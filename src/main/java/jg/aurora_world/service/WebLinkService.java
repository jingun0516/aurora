package jg.aurora_world.service;

import jakarta.persistence.EntityNotFoundException;
import jg.aurora_world.dto.request.WebLinkRequest;
import jg.aurora_world.entity.Users;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.entity.WebLinkPermission;
import jg.aurora_world.enums.PermissionType;
import jg.aurora_world.repository.WebLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebLinkService {
    private final WebLinkRepository webLinkRepository;
    private final UsersService usersService;
    private final WebLinkPermissionService webLinkPermissionService;

    public WebLink getById(Long id) {
        return webLinkRepository.findById(id).orElse(null);
    }

    public List<WebLink> getAll() {
        return webLinkRepository.findAll();
    }

    public List<WebLink> getAllWebLinkByPermissionType(PermissionType permissionType) {
        Long userId = usersService.getLoggedInUser().getId();
        List<WebLinkPermission> webLinkPermissions = webLinkPermissionService.getAllWebLinkByPermissionType(userId, permissionType);
        List<WebLink> webLinks = new ArrayList<>();
        for(WebLinkPermission webLinkPermission : webLinkPermissions) {
            webLinks.add(webLinkPermission.getWebLink());
        }

        return webLinks;
    }

    public List<WebLink> getAllMyWebLinks() {
        Users user = usersService.getLoggedInUser();
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }
        String loginId = user.getLoginId();

        return webLinkRepository.findAllByCreatedBy(loginId);
    }

    public WebLink addWebLink(WebLinkRequest request) {
        WebLink webLink= request.toEntity();
        Users user = usersService.getLoggedInUser();

        if(user == null) {
            throw new EntityNotFoundException("user not found");
        }
        webLink.setCreatedBy(user.getLoginId());
        webLinkRepository.save(webLink);

        return webLink;
    }

    public WebLink updateWebLink(WebLinkRequest request, Long webLinkId) {
        WebLink webLink = webLinkRepository.findById(webLinkId).orElse(null);
        if(webLink == null) {
            return null;
        }

        return webLinkRepository.save(request.updateEntity(webLink));
    }

    public void deleteWebLink(Long webLinkId) {
        if(!webLinkRepository.existsById(webLinkId)) {
            throw new EntityNotFoundException("해당 웹 링크가 존재하지 않습니다.");
        }
        webLinkRepository.deleteById(webLinkId);
    }
}
