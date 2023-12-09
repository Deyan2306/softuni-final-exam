package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.objects.Comment;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals("anonymousUser")) {
            return new ModelAndView("redirect:home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        UserEntity currentUser = this.userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Comment> lastCreatedComments = this.userService.getLatestCreatedComments(currentUser);

        return new ModelAndView("home")
                .addObject("latestComments", lastCreatedComments);
    }

    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }
}
