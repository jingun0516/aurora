package jg.aurora_world.config;

import jg.aurora_world.entity.Users;
import jg.aurora_world.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UsersDetailService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByLoginId(username);

        if(user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return new User(user.getLoginId(), user.getPassword(), new ArrayList<>()); // 권한 정보는 필요에 따라 추가
    }
}
