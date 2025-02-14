package jg.aurora_world.dto.request;

import jg.aurora_world.entity.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersRequest {
    private String loginId;
    private String password;

    public Users toEntity() {
        return new Users(loginId, password);
    }
}
