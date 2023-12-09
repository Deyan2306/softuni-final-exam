package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView myProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            return new ModelAndView("redirect:/");
        }

        UserEntity currentUser = userService.getUserByUsername(authentication.getName());
        boolean isUserActive = userService.isUserActive(currentUser);

        return new ModelAndView("profile")
                .addObject("user", currentUser)
                .addObject("isActive", isUserActive);
    }

    @GetMapping("/profile/movies")
    public ModelAndView myWatchedMovies() {
        return new ModelAndView("watched_movies");
    }

    @GetMapping("/profile/tv-series")
    public ModelAndView myWatchedTVSeries() {
        return new ModelAndView("not-constructed");
    }

    @GetMapping("/settings")
    public ModelAndView myProfileSettings() {
        return new ModelAndView("profile_settings");
    }

    @PostMapping("/settings")
    public ModelAndView savedProfileSettings(UserChangeInformationDTO userChangeInformationDTO) throws IOException {
        boolean successful = this.userService.changeProfileInformation(userChangeInformationDTO);

        return new ModelAndView("redirect:../home");
    }

}
