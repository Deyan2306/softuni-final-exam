package bg.softuni.movieapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tv-series")
public class TVSeriesController {

    @GetMapping("/")
    public ModelAndView moviesHome() {
        return new ModelAndView("tv-series");
    }

    @GetMapping("/{tvSeriesId}")
    public ModelAndView tvSeriesPage(@PathVariable("tvSeriesId") String tvSeriesId) {
        return new ModelAndView("tv-series-page");
    }

}
