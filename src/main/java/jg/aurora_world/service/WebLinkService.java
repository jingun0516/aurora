package jg.aurora_world.service;

import jakarta.persistence.EntityNotFoundException;
import jg.aurora_world.dto.request.WebLinkRequest;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.repository.WebLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebLinkService {
    private final WebLinkRepository webLinkRepository;

    public WebLink getById(Long id) {
        return webLinkRepository.findById(id).orElse(null);
    }

    public List<WebLink> getAll() {
        return webLinkRepository.findAll();
    }

    public WebLink addWebLink(WebLinkRequest request) {
        return webLinkRepository.save(request.toEntity());
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
