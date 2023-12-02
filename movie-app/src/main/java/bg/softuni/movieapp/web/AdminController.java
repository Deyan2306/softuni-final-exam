package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.admin.*;
import bg.softuni.movieapp.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ActorService actorService;

    @Autowired
    public AdminController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }

    @GetMapping("/add/movie")
    public ModelAndView addMoviePage(@ModelAttribute("movieAddDataTransferObject") AdminMovieAddDTO adminMovieAddDTO) {
        return new ModelAndView("add-movie");
    }

    @PostMapping("/add/movie")
    public ModelAndView addMoviePage(
            @ModelAttribute("movieAddDataTransferObject") @Valid AdminMovieAddDTO adminMovieAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-movie");
    }

    @GetMapping("/add/tv-series")
    public ModelAndView addTVSeriesPage(@ModelAttribute("tvSeriesAddDataTransferObject") AdminTVSeriesAddDTO adminTVSeriesAddDTO) {
        return new ModelAndView("add-tv-series");
    }

    @PostMapping("/add/tv-series")
    public ModelAndView addTVSeriesPage(
            @ModelAttribute("tvSeriesAddDataTransferObject") @Valid AdminTVSeriesAddDTO adminTVSeriesAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-tv-series");
    }

    @GetMapping("/add/tv-series-episode")
    public ModelAndView addTVSeriesEpisodePage(@ModelAttribute("tvSeriesEpisodeAddDataTransferObject") AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO) {
        return new ModelAndView("add-tv-series-episode");
    }

    @PostMapping("/add/tv-series-episode")
    public ModelAndView addTVSeriesEpisodePage(
            @ModelAttribute("tvSeriesEpisodeAddDataTransferObject") @Valid AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-tv-series-episode");
    }

    @GetMapping("/add/actor")
    public ModelAndView addActorPage(@ModelAttribute("actorAddDataTransferObject") AdminActorAddDTO adminActorAddDTO) {
        return new ModelAndView("/add-actor");
    }

    @PostMapping("/add/actor")
    public ModelAndView addActorPage(
            @ModelAttribute("actorAddDataTransferObject") @Valid AdminActorAddDTO adminActorAddDTO,
            BindingResult bindingResult) {

        // TODO: FIX
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("/add-actor");
//        }

        boolean successfulActorAdding = this.actorService.addActor(adminActorAddDTO);

        if (!successfulActorAdding) {
            ModelAndView modelAndView = new ModelAndView("add-actor");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }

        return new ModelAndView("../../admin");
    }

    @GetMapping("/edit/actor/{id}")
    public ModelAndView actorEditPage(@PathVariable("id") UUID id, @ModelAttribute("actorEditDataTransferObject") AdminActorAddDTO actorAddDTO) {
        // Auto-populate the fields with the information about the actor
        return new ModelAndView("/add-actor");
    }

    @PostMapping("/edit/actor/{id}")
    public ModelAndView actorEditPage(@PathVariable("id") UUID id, @ModelAttribute("actorEditDataTransferObject") @Valid AdminActorAddDTO actorAddDTO, BindingResult bindingResult) {

        return new ModelAndView("/add-actor");
    }

    @GetMapping("/add/director")
    public ModelAndView addDirectorPage(@ModelAttribute("directorAddDataTransferObject") AdminDirectorAddDTO adminDirectorAddDTO) {
        return new ModelAndView("add-director");
    }

    @PostMapping("/add/director")
    public ModelAndView addDirectorPage(
            @ModelAttribute("directorAddDataTransferObject") @Valid AdminDirectorAddDTO adminDirectorAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-director");
    }

    @GetMapping("/add/studio")
    public ModelAndView addStudioPage(@ModelAttribute("studioAddDataTransferObject") AdminAddStudioDTO adminAddStudioDTO) {
        return new ModelAndView("add-studio");
    }

    @PostMapping("/add/studio")
    public ModelAndView addStudioPage(
            @ModelAttribute("studioAddDataTransferObject") @Valid AdminAddStudioDTO adminAddStudioDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-studio");
    }

}
