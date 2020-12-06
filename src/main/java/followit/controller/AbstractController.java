package followit.controller;

import followit.domain.User;
import org.springframework.web.servlet.view.RedirectView;

public abstract class AbstractController {

    protected static User loggedUser;

    protected RedirectView redirectToHome() {
        if (loggedUser == null) return new RedirectView("");
        return new RedirectView("/logged/" + loggedUser.getLogin());
    }
}
