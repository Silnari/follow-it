package followit.controller;

import followit.domain.User;
import followit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class UserController extends AbstractController {

    private final UserService userService;

    @PostMapping("/add")
    public RedirectView addUser(@ModelAttribute User newUser, RedirectAttributes redirectAttributes) {
        if (userService.isUserInDb(newUser.getLogin()))
            redirectAttributes.addFlashAttribute("addedMessage", "User with given login already exist!");
        else {
            userService.addUser(newUser);
            redirectAttributes.addFlashAttribute("addedMessage", "User added successfully!");
        }
        return redirectToHome();
    }

    @PostMapping("/find")
    public RedirectView find(@ModelAttribute User userToFound, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("foundedUser", userService.findUser(userToFound.getLogin()));
        System.out.println(((User) redirectAttributes.getFlashAttributes().get("foundedUser")).getLogin());
        return redirectToHome();
    }

    @PostMapping("/follow")
    public RedirectView follow(@ModelAttribute User toFollow) {
        userService.follow(loggedUser, toFollow);
        return redirectToHome();
    }

    @PostMapping("/unfollow")
    public RedirectView unfollow(@ModelAttribute User toUnfollow) {
        userService.unfollow(loggedUser, toUnfollow);
        return redirectToHome();
    }
}
