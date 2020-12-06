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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("recommendedUsers", userService.getAll());
        model.addAttribute("newUser", new User());
        return "index";
    }

    @GetMapping("/logged/{login}")
    public String home(Model model, @PathVariable String login, RedirectAttributes redirectAttributes) {
        User loggedUser = userService.isUserInDb(login) ? userService.findUser(login) : null;
        AbstractController.loggedUser = loggedUser;

        model.addAttribute("newUser", new User());
        model.addAttribute("newPost", new Post());
        model.addAttribute("userToFind", new User());
        model.addAttribute("postList", postService.getPostsForUser(loggedUser));
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("userList", userService.getAll());
        model.addAttribute("recommendedUsers", userService.getRecommendedToFollow(loggedUser));
        model.addAttribute("followedUsers", loggedUser.getFollowing());
        model.addAttribute("numberOfFollowing", userService.getFollowingNumber(loggedUser.getLogin()));
        model.addAttribute("newFollowing", new User());
        return "index";
    }
}
