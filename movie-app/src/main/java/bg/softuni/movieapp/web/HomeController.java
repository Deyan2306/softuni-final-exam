package bg.softuni.movieapp.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            return new ModelAndView("redirect:home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }
}
