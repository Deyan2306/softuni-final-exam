package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.admin.*;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    private final DirectorService directorService;
    private final ActorService actorService;
    private final StudioService studioService;
    private final MovieService movieService;
    private final TVSeriesService tvSeriesService;
    private final ActorRoleService actorRoleService;
    private final TVSeriesEpisodeService tvSeriesEpisodeService;
    private final CommentService commentService;
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public AdminController(ActorService actorService, DirectorService directorService1, StudioService studioService, MovieService movieService, TVSeriesService tvSeriesService, ActorRoleService actorRoleService, TVSeriesEpisodeService tvSeriesEpisodeService, CommentService commentService, QuoteService quoteService, UserService userService) {
        this.actorService = actorService;
        this.directorService = directorService1;
        this.studioService = studioService;
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
        this.actorRoleService = actorRoleService;
        this.tvSeriesEpisodeService = tvSeriesEpisodeService;
        this.commentService = commentService;
        this.quoteService = quoteService;
        this.userService = userService;
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
            @RequestParam("genres") List<String> genres,
            @RequestParam("actorId") List<String> actorIds,
            @RequestParam("titlePicture") MultipartFile titlePicture,
            BindingResult bindingResult) {

        adminMovieAddDTO.setGenres(genres);
        adminMovieAddDTO.setActorRoleIDs(actorIds);
        adminMovieAddDTO.setTitlePicture(titlePicture);

        boolean successfulMovieAdding = this.movieService.addMovie(adminMovieAddDTO);

        if (!successfulMovieAdding) {
            ModelAndView modelAndView = new ModelAndView("add-movie");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:../../admin");
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
            @RequestParam("titleImage") MultipartFile titleImage,
            BindingResult bindingResult) {

        adminTVSeriesEpisodeDTO.setTitleImage(titleImage);

        boolean successfulTvSeriesEpisodeAdding = this.tvSeriesEpisodeService.addEpisode(adminTVSeriesEpisodeDTO);

        if (!successfulTvSeriesEpisodeAdding) {
            ModelAndView modelAndView = new ModelAndView("add-tv-series-episode");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:../../admin");
    }

    @GetMapping("/admin/add/actor")
    public ModelAndView addActorPage(@ModelAttribute("actorAddDataTransferObject") AdminActorAddDTO adminActorAddDTO) {
        return new ModelAndView("/add-actor");
    }

    @PostMapping("/admin/add/actor")
    public ModelAndView addActorPage(
            @ModelAttribute("actorAddDataTransferObject") @Valid AdminActorAddDTO adminActorAddDTO,
            @RequestParam("actorPhoto") MultipartFile actorPhoto,
            BindingResult bindingResult) {

        adminActorAddDTO.setActorPhoto(actorPhoto);

        boolean successfulActorAdding = this.actorService.addActor(adminActorAddDTO);

        if (!successfulActorAdding) {
            ModelAndView modelAndView = new ModelAndView("add-actor");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:../../admin");
    }

    @GetMapping("/admin/actor/edit/all")
    public ModelAndView allActors() {

        List<Actor> actors = this.actorService.getAllActors();

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("all-actors-edit")
                .addObject("actors", actors)
                .addObject("profilePhotoUri", imageUrl);
    }

    @GetMapping("/admin/add/actor-role")
    public ModelAndView addActorRolePage(@ModelAttribute("actorRoleAddDataTransferObject") AdminAddActorRoleDTO adminAddActorRoleDTO) {
        return new ModelAndView("add-actor-role");
    }

    @PostMapping("/admin/add/actor-role")
    public ModelAndView addActorRolePage(
            @ModelAttribute("actorRoleAddDataTransferObject") @Valid AdminAddActorRoleDTO adminAddActorRoleDTO,
            BindingResult bindingResult) {

        boolean successfulActorRoleAdding = this.actorRoleService.addActorRole(adminAddActorRoleDTO);

        if (!successfulActorRoleAdding) {
            ModelAndView modelAndView = new ModelAndView("add-actor-role");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:../../admin");
    }

    @GetMapping("/admin/add/director")
    public ModelAndView addDirectorPage(@ModelAttribute("directorAddDataTransferObject") AdminDirectorAddDTO adminDirectorAddDTO) {
        return new ModelAndView("add-director");
    }

    @PostMapping("/admin/add/director")
    public ModelAndView addDirectorPage(
            @ModelAttribute("directorAddDataTransferObject") @Valid AdminDirectorAddDTO adminDirectorAddDTO,
            @RequestParam("directorPhoto") MultipartFile directorPhoto,
            BindingResult bindingResult) throws IOException {

        adminDirectorAddDTO.setDirectorPicture(directorPhoto);


        boolean successfulDirectorAdding = this.directorService.addDirector(adminDirectorAddDTO);

        if (!successfulDirectorAdding) {
            ModelAndView modelAndView = new ModelAndView("/add-director");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:../../admin");
    }

    @GetMapping("/admin/add/studio")
    public ModelAndView addStudioPage(@ModelAttribute("studioAddDataTransferObject") AdminAddStudioDTO adminAddStudioDTO) {
        return new ModelAndView("add-studio");
    }

    @PostMapping("/admin/add/studio")
    public ModelAndView addStudioPage(
            @ModelAttribute("studioAddDataTransferObject") @Valid AdminAddStudioDTO adminAddStudioDTO,
            @RequestParam("studioPhoto") MultipartFile studioPhoto,
            BindingResult bindingResult) {

        adminAddStudioDTO.setStudioPicture(studioPhoto);

        boolean successfulStudioAdding = this.studioService.addStudio(adminAddStudioDTO);

        if (!successfulStudioAdding) {
            ModelAndView modelAndView = new ModelAndView("/add-studio");
            modelAndView.addObject("hasAddingError", true);
            return modelAndView;
        }


        return new ModelAndView("redirect:../../admin");
    }

}
