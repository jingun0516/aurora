package jg.aurora_world.repository;

import jg.aurora_world.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Boolean existsByLoginId(String loginId);
    Users findByLoginId(String loginId);
}
