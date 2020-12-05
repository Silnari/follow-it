package followit.controller;

import followit.domain.User;
import followit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("addedMessage", null);
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        if (userService.isUserInDb(user.getLogin())) {
            model.addAttribute("addedMessage", "User with given login already exist!");
        } else {
            userService.addUser(user);
            model.addAttribute("addedMessage", "User added successfully!");
        }
        return "user/add";
    }
}
