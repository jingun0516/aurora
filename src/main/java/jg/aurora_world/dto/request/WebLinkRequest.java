package jg.aurora_world.dto.request;

import jg.aurora_world.entity.WebLink;
import jg.aurora_world.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebLinkRequest {
    private String name;
    private String url;
    private Category category;

    public WebLink toEntity() {
        return new WebLink(name, url, category);
    }

    public WebLink updateEntity(WebLink webLink) {
        if(name != null) {
            webLink.setName(name);
        }
        if(url != null) {
            webLink.setUrl(url);
        }
        if(category != null) {
            webLink.setCategory(category);
        }
        return webLink;
    }
}
