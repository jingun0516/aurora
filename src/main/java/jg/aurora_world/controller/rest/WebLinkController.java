package jg.aurora_world.controller.rest;

import jakarta.persistence.EntityNotFoundException;
import jg.aurora_world.dto.request.WebLinkRequest;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.entity.WebLinkPermission;
import jg.aurora_world.enums.PermissionType;
import jg.aurora_world.service.WebLinkPermissionService;
import jg.aurora_world.service.WebLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weblink")
public class WebLinkController {
    private final WebLinkService webLinkService;
    private final WebLinkPermissionService webLinkPermissionService;

    @PostMapping
    public ResponseEntity<WebLink> addWebLink(@RequestBody WebLinkRequest request) {
        return ResponseEntity.ok(webLinkService.addWebLink(request));
    }

    @GetMapping
    public ResponseEntity<List<WebLink>> getAll() {
        return ResponseEntity.ok(webLinkService.getAll());
    }

    @PutMapping("/{webLinkId}")
    public ResponseEntity<WebLink> update(@PathVariable Long webLinkId, @RequestBody WebLinkRequest request) {
        return ResponseEntity.ok(webLinkService.updateWebLink(request, webLinkId));
    }

    @DeleteMapping("/{webLinkId}")
    public ResponseEntity<Void> delete(@PathVariable Long webLinkId) {
        webLinkService.deleteWebLink(webLinkId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<WebLink>> getReadableWebLinks() {
        return ResponseEntity.ok(webLinkService.getAllWebLinkByPermissionType(PermissionType.READ));
    }

    @PostMapping("/read")
    public ResponseEntity<?> addReadableWebLink(@RequestParam Long userId, @RequestParam Long webLinkId) {
        try {
            WebLinkPermission readPermission = webLinkPermissionService.addWebLinkPermission(userId, webLinkId, PermissionType.READ);
            return ResponseEntity.ok(readPermission);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or WebLink not found.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Permission already exists.");
        }
    }

    @PostMapping("/write")
    public ResponseEntity<?> addWritableWebLink(@RequestParam Long userId, @RequestParam Long webLinkId) {
        try {
            WebLinkPermission readPermission = webLinkPermissionService.addWebLinkPermission(userId, webLinkId, PermissionType.READ);
        } catch (IllegalStateException e) {

        }

        try {
            WebLinkPermission writePermission = webLinkPermissionService.addWebLinkPermission(userId, webLinkId, PermissionType.WRITE);
            return ResponseEntity.ok(writePermission);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or WebLink not found.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Permission already exists.");
        }
    }


    @DeleteMapping("/read")
    public ResponseEntity<Void> deleteReadableWebLink(@RequestParam Long userId, @RequestParam Long webLinkId) {
        boolean readDeleted = webLinkPermissionService.deleteWebLinkPermission(userId, webLinkId, PermissionType.READ);
        boolean writeDeleted = webLinkPermissionService.deleteWebLinkPermission(userId, webLinkId, PermissionType.WRITE);

        if (readDeleted || writeDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/write")
    public ResponseEntity<Void> deleteWritableWebLink(@RequestParam Long userId, @RequestParam Long webLinkId) {
        boolean writeDeleted = webLinkPermissionService.deleteWebLinkPermission(userId, webLinkId, PermissionType.WRITE);

        if (writeDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
