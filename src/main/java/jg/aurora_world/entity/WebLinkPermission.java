package jg.aurora_world.entity;

import jakarta.persistence.*;
import jg.aurora_world.enums.PermissionType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WebLinkPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "weblink_id")
    private WebLink webLink;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;
}
