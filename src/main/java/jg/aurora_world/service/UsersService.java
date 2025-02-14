package jg.aurora_world.service;

import jg.aurora_world.dto.request.UsersRequest;
import jg.aurora_world.entity.Users;
import jg.aurora_world.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Users register(UsersRequest request) {
        if(checkLoginId(request.getLoginId())) {
            return null;
        }

        return usersRepository.save(request.toEntity());
    }

    public Boolean checkLoginId(String loginId) {
        return usersRepository.existsByLoginId(loginId);
    }
}
