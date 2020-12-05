package followit.controller;

import followit.domain.Post;
import followit.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class PostController extends AbstractController {

    private final PostService postService;

    @PostMapping("/add-post")
    private RedirectView addPost(@ModelAttribute Post post) {
        post.setPostedAt(new Date());
        post.setAuthor(loggedUser);
        postService.addPost(post);
        return redirectToHome();
    }
}
