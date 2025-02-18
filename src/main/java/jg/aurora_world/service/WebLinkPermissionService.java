package jg.aurora_world.service;

import jakarta.persistence.EntityNotFoundException;
import jg.aurora_world.entity.Users;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.entity.WebLinkPermission;
import jg.aurora_world.enums.PermissionType;
import jg.aurora_world.repository.WebLinkPermissionRepository;
import jg.aurora_world.repository.WebLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebLinkPermissionService {
    private final WebLinkPermissionRepository webLinkPermissionRepository;
    private final UsersService usersService;
    private final WebLinkRepository webLinkRepository;

    @Transactional
    public WebLinkPermission addWebLinkPermission(Long userId, Long webLinkId, PermissionType permissionType) {
        Users user = usersService.getById(userId);
        WebLink webLink = webLinkRepository.findById(webLinkId).orElse(null);

        if (user == null || webLink == null) {
            throw new EntityNotFoundException("User or WebLink not found");
        }

        boolean exists = webLinkPermissionRepository.existsByUsersAndWebLinkAndPermissionType(user, webLink, permissionType);
        if (exists) {
            throw new IllegalStateException("Permission already exists.");
        }

        WebLinkPermission webLinkPermission = new WebLinkPermission();
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

    public Boolean canWriteWebLink(Long userId, Long webLinkId) {
        Users user = usersService.getById(userId);
        WebLink webLink = webLinkRepository.findById(webLinkId).orElse(null);
        if(user == null || webLink == null) {
            return false;
        }
        return webLinkPermissionRepository.existsByUsersAndWebLinkAndPermissionType(user, webLink, PermissionType.WRITE);
    }

    @Transactional
    public boolean deleteWebLinkPermission(Long userId, Long webLinkId, PermissionType permissionType) {
        Users user = usersService.getById(userId);
        WebLink webLink = webLinkRepository.findById(webLinkId).orElse(null);
        if(user == null || webLink == null) {
            throw new EntityNotFoundException("user or webLink not found");
        }
        if(!webLinkPermissionRepository.existsByUsersAndWebLinkAndPermissionType(user, webLink, permissionType)) {
            return false;
        }

        webLinkPermissionRepository.deleteByUsersAndWebLinkAndPermissionType(user, webLink, permissionType);
        return true;
    }
}
