package bg.softuni.movieapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController {

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

}
