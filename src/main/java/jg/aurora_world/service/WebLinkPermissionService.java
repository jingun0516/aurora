package jg.aurora_world.service;

import jg.aurora_world.entity.Users;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.entity.WebLinkPermission;
import jg.aurora_world.enums.PermissionType;
import jg.aurora_world.repository.WebLinkPermissionRepository;
import jg.aurora_world.repository.WebLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebLinkPermissionService {
    private final WebLinkPermissionRepository webLinkPermissionRepository;
    private final UsersService usersService;
    private final WebLinkRepository webLinkRepository;

    public WebLinkPermission addWebLinkPermission(Long userId, Long webLinkId, PermissionType permissionType) {
        WebLinkPermission webLinkPermission = new WebLinkPermission();
        Users user = usersService.getById(userId);
        WebLink webLink = webLinkRepository.findById(webLinkId).orElse(null);

        if(user == null || webLink == null) {
            return null;
        }

        webLinkPermission.setUsers(user);
        webLinkPermission.setWebLink(webLink);
        webLinkPermission.setPermissionType(permissionType);
        return webLinkPermissionRepository.save(webLinkPermission);
    }

    public List<WebLinkPermission> getAllWebLinkByPermissionType(Long userId, PermissionType permissionType) {
        Users user = usersService.getById(userId);
        List<WebLinkPermission> permissions = webLinkPermissionRepository.findAllByUsers(user);
        List<WebLinkPermission> permissionsByType = new ArrayList<>();

        for(WebLinkPermission permission : permissions) {
            if(permission.getPermissionType() == permissionType) {
                permissionsByType.add(permission);
            }
        }

        return permissionsByType;
    }
}
