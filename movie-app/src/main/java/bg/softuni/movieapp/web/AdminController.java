package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }

    @GetMapping("/add/movie")
    public ModelAndView addMoviePage() {
        return new ModelAndView("add-movie");
    }

    @GetMapping("/add/tv-series")
    public ModelAndView addTVSeries() {
        return new ModelAndView("add-tv-series");
    }

    @GetMapping("/add/tv-series-episode")
    public ModelAndView addTVSeriesEpisode() {
        return new ModelAndView("add-tv-series-episode");
    }

    @GetMapping("/add/actor")
    public ModelAndView addActor(@ModelAttribute("actorAddDataTransferObject") AdminActorAddDTO adminActorAddDTO) {
        return new ModelAndView("add-actor");
    }

    @PostMapping("/add/actor")
    public ModelAndView addActor(
            @ModelAttribute("actorAddDataTransferObject") @Valid AdminActorAddDTO adminActorAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-actor");
    }

    @GetMapping("/add/director")
    public ModelAndView addDirector() {
        return new ModelAndView("add-director");
    }

    @GetMapping("/add/studio")
    public ModelAndView addStudio() {
        return new ModelAndView("add-studio");
    }

}
