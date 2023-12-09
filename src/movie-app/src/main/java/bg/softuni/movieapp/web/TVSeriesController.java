package bg.softuni.movieapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TVSeriesController {

    @GetMapping("/tv-series")
    public ModelAndView tvSeriesHome() {
        return new ModelAndView("not-constructed");
    }

}
