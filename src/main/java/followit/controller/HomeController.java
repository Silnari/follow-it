package followit.controller;

import followit.domain.Post;
import followit.domain.User;
import followit.service.PostService;
import followit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController extends AbstractController {

    private final UserService userService;

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("loggedUser", null);
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("postList", postService.getAll());
        return "index";
    }

    @GetMapping("/logged/{login}")
    public String home(Model model, @PathVariable String login) {
        User loggedUser = userService.isUserInDb(login) ? userService.findUser(login) : null;
        AbstractController.loggedUser = loggedUser;

        model.addAttribute("newPost", new Post());
        model.addAttribute("postList", postService.getAll());
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("userList", userService.getAll());
        return "index";
    }
}
