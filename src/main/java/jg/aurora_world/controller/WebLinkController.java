package jg.aurora_world.controller;

import jg.aurora_world.dto.request.WebLinkRequest;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.service.WebLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weblink")
public class WebLinkController {
    private final WebLinkService webLinkService;

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
}
