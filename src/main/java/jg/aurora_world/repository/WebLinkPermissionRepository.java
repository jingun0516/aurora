package jg.aurora_world.repository;

import jg.aurora_world.entity.Users;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.entity.WebLinkPermission;
import jg.aurora_world.enums.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebLinkPermissionRepository extends JpaRepository<WebLinkPermission, Long> {
    List<WebLinkPermission> findAllByUsers(Users user);
    Boolean existsByUsersAndWebLinkAndPermissionType(Users user, WebLink webLink, PermissionType permissionType);
    void deleteByUsersAndWebLinkAndPermissionType(Users user, WebLink webLink, PermissionType permissionType);
}