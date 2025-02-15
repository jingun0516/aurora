package jg.aurora_world.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "users")
    private List<WebLinkPermission> linkPermissions = new ArrayList<>();

    public Users(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
