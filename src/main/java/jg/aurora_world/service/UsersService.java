package jg.aurora_world.service;

import jg.aurora_world.dto.request.UsersRequest;
import jg.aurora_world.entity.Users;
import jg.aurora_world.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Users getById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public List<Users> getUsers() {
        List<Users> users = usersRepository.findAll();
        users.remove(getLoggedInUser());

        return users;
    }

    public Users register(UsersRequest request) {
        Users user = request.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return usersRepository.save(user);
    }

    public Boolean checkLoginId(String loginId) {
        return usersRepository.existsByLoginId(loginId);
    }

    public Users getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        String loginId = authentication.getName();

        return usersRepository.findByLoginId(loginId);
    }
}
