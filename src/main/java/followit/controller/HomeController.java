package followit.controller;

import followit.domain.User;
import followit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("loggedUser", null);
        model.addAttribute("userList", userService.getAll());
        return "index";
    }

    @GetMapping("/{user}")
    public String home(Model model, @PathVariable String user) {
        model.addAttribute("loggedUser", userService.isUserInDb(user) ? userService.findUser(user) : null);
        model.addAttribute("userList", userService.getAll());
        return "index";
    }
}
