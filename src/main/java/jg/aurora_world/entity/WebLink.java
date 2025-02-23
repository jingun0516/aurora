package jg.aurora_world.entity;

import jakarta.persistence.*;
import jg.aurora_world.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WebLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "webLink")
    private List<WebLinkPermission> linkPermissions = new ArrayList<>();

    public WebLink(String name, String url, Category category) {
        this.name = name;
        this.url = url;
        this.category = category;
    }
}
