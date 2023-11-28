package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel) {

        boolean isRegistered = this.userService.register(userRegisterBindingModel);

        String view = isRegistered
                ? "redirect:/login"
                : "register";

        return new ModelAndView(view);

    }

}
