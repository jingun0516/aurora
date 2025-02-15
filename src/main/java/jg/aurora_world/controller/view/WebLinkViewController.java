package jg.aurora_world.controller.view;

import jg.aurora_world.entity.WebLink;
import jg.aurora_world.enums.PermissionType;
import jg.aurora_world.service.WebLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/weblink")
@RequiredArgsConstructor
public class WebLinkViewController {
    private final WebLinkService webLinkService;

    @GetMapping("/register")
    public String webLinkRegister() {
        return "weblink-register";
    }

    @GetMapping("/all")
    public String readableWebLink(Model model) {
        List<WebLink> webLinks = webLinkService.getAllWebLinkByPermissionType(PermissionType.READ);
        model.addAttribute("webLinks", webLinks);

        return "weblinks";
    }
}
