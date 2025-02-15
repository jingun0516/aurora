package jg.aurora_world.controller.rest;

import jg.aurora_world.dto.request.UsersRequest;
import jg.aurora_world.entity.Users;
import jg.aurora_world.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> register(@RequestBody UsersRequest request) {
        Users registeredUser = usersService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/check-id")
    public ResponseEntity<?> checkLoginId(@RequestParam String loginId) {
        if (usersService.checkLoginId(loginId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("이미 존재하는 아이디입니다.");
        }
        return ResponseEntity.ok().build();
    }
}
