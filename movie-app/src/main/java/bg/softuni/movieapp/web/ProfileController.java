package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView myProfile() {
        return new ModelAndView("profile");
    }

    @GetMapping("/movies")
    public ModelAndView myWatchedMovies() {
        return new ModelAndView("watched_movies");
    }

    @GetMapping("/tv-series")
    public ModelAndView myWatchedTVSeries() {
        return new ModelAndView("watched_tv_series");
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
