package bg.softuni.movieapp.web;

import bg.softuni.movieapp.services.impl.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;

    @Autowired
    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/hello") // TODO: Delete this later
    public ModelAndView hello() { return new ModelAndView("hello"); }

    @GetMapping("/login")
    public ModelAndView login() { return new ModelAndView("login"); }
}
