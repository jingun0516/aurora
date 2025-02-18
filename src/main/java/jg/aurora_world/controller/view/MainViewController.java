package jg.aurora_world.controller.view;

import jg.aurora_world.entity.Users;
import jg.aurora_world.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainViewController {
    private final UsersService usersService;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String users(@RequestParam Long weblink_id, Model model) {
        List<Users> users = usersService.getUsers();
        model.addAttribute("users", users);

        if (weblink_id != null) {
            model.addAttribute("weblink_id", weblink_id);
        }

        return "users";
    }
}
