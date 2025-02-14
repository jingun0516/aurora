package jg.aurora_world.controller;

import jg.aurora_world.dto.request.UsersRequest;
import jg.aurora_world.entity.Users;
import jg.aurora_world.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> register(@RequestBody UsersRequest request) {
        return ResponseEntity.ok(usersService.register(request));
    }
}
