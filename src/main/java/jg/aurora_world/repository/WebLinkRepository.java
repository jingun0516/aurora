package jg.aurora_world.repository;

import jg.aurora_world.entity.WebLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebLinkRepository extends JpaRepository<WebLink, Long> {
    List<WebLink> findAllByCreatedBy(String createdBy);
}
