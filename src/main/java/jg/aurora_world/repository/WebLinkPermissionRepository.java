package jg.aurora_world.repository;

import jg.aurora_world.entity.Users;
import jg.aurora_world.entity.WebLinkPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebLinkPermissionRepository extends JpaRepository<WebLinkPermission, Long> {
    List<WebLinkPermission> findAllByUsers(Users user);
}