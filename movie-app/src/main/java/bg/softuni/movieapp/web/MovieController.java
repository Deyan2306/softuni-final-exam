package bg.softuni.movieapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @GetMapping("/")
    public ModelAndView moviesHome() {
        return new ModelAndView("movies");
    }

    @GetMapping("/{movieId}")
    public ModelAndView viewMoviePage(@PathVariable("movieId") String movieId) {
        return new ModelAndView("movie-page");
    }

}
