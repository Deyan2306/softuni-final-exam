package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.admin.*;
import bg.softuni.movieapp.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class AdminController {

    private final ActorService actorService;
    private final MovieService movieService;
    private final TVSeriesService tvSeriesService;
    private final TVSeriesEpisodeService tvSeriesEpisodeService;
    private final CommentService commentService;
    private final QuoteService quoteService;


    @Autowired
    public AdminController(ActorService actorService, DirectorService directorService, MovieService movieService, TVSeriesService tvSeriesService, TVSeriesEpisodeService tvSeriesEpisodeService, CommentService commentService, QuoteService quoteService) {
        this.actorService = actorService;
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
        this.tvSeriesEpisodeService = tvSeriesEpisodeService;
        this.commentService = commentService;
        this.quoteService = quoteService;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {

        int numberOfActors = this.actorService.getNumberOfActors();
        int numberOfMovies = this.movieService.getNumberOfMovies();
        int numberOfTVSeries = this.tvSeriesService.getNumberOfTVSeries();
        int numberOfTVSeriesEpisodes = this.tvSeriesEpisodeService.getNumberOfTVSeriesEpisodes();
        int numberOfComments = this.commentService.getNumberOfComments();
        int numberOfQuotes = this.quoteService.getNumberOfQuotes();

        return new ModelAndView("admin")
                .addObject("number_of_actors", numberOfActors)
                .addObject("number_of_movies", numberOfMovies)
                .addObject("number_of_tv_series", numberOfTVSeries)
                .addObject("number_of_tv_series_episodes", numberOfTVSeriesEpisodes)
                .addObject("number_of_comments", numberOfComments)
                .addObject("number_of_quotes", numberOfQuotes);
    }

    @GetMapping("/admin/add/movie")
    public ModelAndView addMoviePage(@ModelAttribute("movieAddDataTransferObject") AdminMovieAddDTO adminMovieAddDTO) {
        return new ModelAndView("add-movie");
    }

    @PostMapping("/admin/add/movie")
    public ModelAndView addMoviePage(
            @ModelAttribute("movieAddDataTransferObject") @Valid AdminMovieAddDTO adminMovieAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-movie");
    }

    @GetMapping("/admin/add/tv-series")
    public ModelAndView addTVSeriesPage(@ModelAttribute("tvSeriesAddDataTransferObject") AdminTVSeriesAddDTO adminTVSeriesAddDTO) {
        return new ModelAndView("add-tv-series");
    }

    @PostMapping("/admin/add/tv-series")
    public ModelAndView addTVSeriesPage(
            @ModelAttribute("tvSeriesAddDataTransferObject") @Valid AdminTVSeriesAddDTO adminTVSeriesAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-tv-series");
    }

    @GetMapping("/admin/add/tv-series-episode")
    public ModelAndView addTVSeriesEpisodePage(@ModelAttribute("tvSeriesEpisodeAddDataTransferObject") AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO) {
        return new ModelAndView("add-tv-series-episode");
    }

    @PostMapping("/admin/add/tv-series-episode")
    public ModelAndView addTVSeriesEpisodePage(
            @ModelAttribute("tvSeriesEpisodeAddDataTransferObject") @Valid AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-tv-series-episode");
    }

    @GetMapping("/admin/add/actor")
    public ModelAndView addActorPage(@ModelAttribute("actorAddDataTransferObject") AdminActorAddDTO adminActorAddDTO) {
        return new ModelAndView("/add-actor");
    }

    @PostMapping("/admin/add/actor")
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

    @GetMapping("/admin/edit/actor/{id}")
    public ModelAndView actorEditPage(@PathVariable("id") UUID id, @ModelAttribute("actorEditDataTransferObject") AdminActorAddDTO actorAddDTO) {
        // Auto-populate the fields with the information about the actor
        return new ModelAndView("/add-actor");
    }

    @PostMapping("/admin/edit/actor/{id}")
    public ModelAndView actorEditPage(@PathVariable("id") UUID id, @ModelAttribute("actorEditDataTransferObject") @Valid AdminActorAddDTO actorAddDTO, BindingResult bindingResult) {

        return new ModelAndView("/add-actor");
    }

    @GetMapping("/admin/add/director")
    public ModelAndView addDirectorPage(@ModelAttribute("directorAddDataTransferObject") AdminDirectorAddDTO adminDirectorAddDTO) {
        return new ModelAndView("add-director");
    }

    @PostMapping("/admin/add/director")
    public ModelAndView addDirectorPage(
            @ModelAttribute("directorAddDataTransferObject") @Valid AdminDirectorAddDTO adminDirectorAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-director");
    }

    @GetMapping("/admin/add/studio")
    public ModelAndView addStudioPage(@ModelAttribute("studioAddDataTransferObject") AdminAddStudioDTO adminAddStudioDTO) {
        return new ModelAndView("add-studio");
    }

    @PostMapping("/admin/add/studio")
    public ModelAndView addStudioPage(
            @ModelAttribute("studioAddDataTransferObject") @Valid AdminAddStudioDTO adminAddStudioDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-studio");
    }

}
