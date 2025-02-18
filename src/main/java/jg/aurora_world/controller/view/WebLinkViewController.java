package jg.aurora_world.controller.view;

import jg.aurora_world.entity.Users;
import jg.aurora_world.entity.WebLink;
import jg.aurora_world.enums.PermissionType;
import jg.aurora_world.service.UsersService;
import jg.aurora_world.service.WebLinkPermissionService;
import jg.aurora_world.service.WebLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/weblink")
@RequiredArgsConstructor
public class WebLinkViewController {
    private final WebLinkService webLinkService;
    private final UsersService usersService;
    private final WebLinkPermissionService webLinkPermissionService;

    @GetMapping("/register")
    public String webLinkRegister() {
        return "weblink-register";
    }

    @GetMapping("/all")
    public String readableWebLink(Model model) {
        Long userId = usersService.getLoggedInUser().getId();
        List<WebLink> myWebLinks = webLinkService.getAllMyWebLinks();
        Map<Long, Boolean> canWriteMap = new HashMap<>();

        List<WebLink> readableWebLinks = webLinkService.getAllWebLinkByPermissionType(PermissionType.READ);

        for (WebLink webLink : readableWebLinks) {
            boolean canWrite = webLinkPermissionService.canWriteWebLink(userId, webLink.getId());
            canWriteMap.put(webLink.getId(), canWrite);
        }

        model.addAttribute("myWebLinks", myWebLinks);
        model.addAttribute("canWriteMap", canWriteMap);
        model.addAttribute("readableWebLinks", readableWebLinks);

        return "weblinks";
    }
}
