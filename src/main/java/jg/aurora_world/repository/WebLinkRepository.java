package jg.aurora_world.repository;

import jg.aurora_world.entity.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebLinkRepository extends JpaRepository<WebLink, Long> {
}
