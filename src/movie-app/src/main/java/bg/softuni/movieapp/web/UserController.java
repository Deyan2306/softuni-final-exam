package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
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

    @PostMapping("login-error")
    public String onFailure(
            @ModelAttribute("username") String username,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);

        return "login";
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel) {

        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

        if (!userAuthentication.getName().equals("anonymousUser")) {
            return new ModelAndView("redirect:../home");
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(
            @ModelAttribute("userRegisterBindingModel") @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult) {

        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();

        if (!userAuthentication.getName().equals("anonymousUser")) {
            return new ModelAndView("redirect:../home");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register");
        }

        boolean successfulRegistration = this.userService.register(userRegisterBindingModel);

        if (!successfulRegistration) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("hasRegistrationError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:/login");
    }

}
