package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.admin.*;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.model.entity.ActorRole;
import bg.softuni.movieapp.model.entity.Director;
import bg.softuni.movieapp.model.entity.Studio;
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
import java.util.Optional;

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
    private final RatingService ratingService;

    @Autowired
    public AdminController(ActorService actorService, DirectorService directorService1, StudioService studioService, MovieService movieService, TVSeriesService tvSeriesService, ActorRoleService actorRoleService, TVSeriesEpisodeService tvSeriesEpisodeService, CommentService commentService, QuoteService quoteService, UserService userService, RatingService ratingService) {
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
        this.ratingService = ratingService;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {

        int numberOfActors = this.actorService.getNumberOfActors();
        int numberOfMovies = this.movieService.getNumberOfMovies();
        int numberOfUsers = this.userService.getNumberOfUsers();
        int numberOfRatings = this.ratingService.getNumberOfRatings();
        int numberOfComments = this.commentService.getNumberOfComments();
        int numberOfQuotes = this.quoteService.getNumberOfQuotes();

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());


        return new ModelAndView("admin")
                .addObject("number_of_actors", numberOfActors)
                .addObject("number_of_movies", numberOfMovies)
                .addObject("number_of_users", numberOfUsers)
                .addObject("number_of_ratings", numberOfRatings)
                .addObject("number_of_comments", numberOfComments)
                .addObject("number_of_quotes", numberOfQuotes)
                .addObject("profilePhotoUri", imageUrl);
    }

    @GetMapping("/admin/add/movie")
    public ModelAndView addMoviePage(@ModelAttribute("movieAddDataTransferObject") AdminMovieAddDTO adminMovieAddDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("add-movie")
                .addObject("profilePhotoUri", imageUrl);
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

    @GetMapping("/admin/movie/delete/{id}")
    public ModelAndView deleteMovie(@PathVariable("id") String movieID) {
        this.movieService.deleteMovieByMovieId(movieID);

        return new ModelAndView("redirect:../../movie/edit/all");
    }

    @GetMapping("/admin/add/tv-series")
    public ModelAndView addTVSeriesPage(@ModelAttribute("tvSeriesAddDataTransferObject") AdminTVSeriesAddDTO adminTVSeriesAddDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("add-tv-series")
                .addObject("profilePhotoUri", imageUrl);
    }

    @PostMapping("/admin/add/tv-series")
    public ModelAndView addTVSeriesPage(
            @ModelAttribute("tvSeriesAddDataTransferObject") @Valid AdminTVSeriesAddDTO adminTVSeriesAddDTO,
            BindingResult bindingResult) {

        return new ModelAndView("add-tv-series");
    }

    @GetMapping("/admin/tv-series/delete/{id}")
    public ModelAndView deleteTvSeries(@PathVariable("id") String tvSeriesId) {
        this.tvSeriesService.deleteTVSeriesById(tvSeriesId);

        return new ModelAndView("redirect:../../tv-series/edit/all");
    }

    @GetMapping("/admin/add/tv-series-episode")
    public ModelAndView addTVSeriesEpisodePage(@ModelAttribute("tvSeriesEpisodeAddDataTransferObject") AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("add-tv-series-episode")
                .addObject("profilePhotoUri", imageUrl);
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

    @GetMapping("/admin/tv-series-episode/delete/{id}")
    public ModelAndView deleteTvSeriesEpisode(@PathVariable("id") String tvSeriesEpisodeId) {
        this.tvSeriesEpisodeService.deleteTVSeriesEpisodeById(tvSeriesEpisodeId);

        return new ModelAndView("redirect:../../tv-series-episode/edit/all");
    }

    @GetMapping("/admin/add/actor")
    public ModelAndView addActorPage(@ModelAttribute("actorAddDataTransferObject") AdminActorAddDTO adminActorAddDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("/add-actor")
                .addObject("profilePhotoUri", imageUrl);
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

    @GetMapping("/admin/actor/delete/{id}")
    public ModelAndView deleteActor(@PathVariable("id") String actorId) {
        this.actorService.deleteActorById(actorId);

        return new ModelAndView("redirect:../../actor/edit/all");
    }

    @GetMapping("/admin/add/actor-role")
    public ModelAndView addActorRolePage(@ModelAttribute("actorRoleAddDataTransferObject") AdminAddActorRoleDTO adminAddActorRoleDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("add-actor-role")
                .addObject("profilePhotoUri", imageUrl);
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

    @GetMapping("/admin/actor-role/edit/all")
    public ModelAndView allActorRoles() {

        List<ActorRole> actorRoles = this.actorRoleService.getAllActorRoles();

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("all-role-actors-edit")
                .addObject("actor_roles", actorRoles)
                .addObject("profilePhotoUri", imageUrl);
    }

    @GetMapping("/admin/actor-role/delete/{id}")
    public ModelAndView deleteActorRole(@PathVariable("id") String actorRoleId) {
        this.actorRoleService.deleteActorRoleById(actorRoleId);

        return new ModelAndView("redirect:../../actor-role/edit/all");
    }

    @GetMapping("/admin/add/director")
    public ModelAndView addDirectorPage(@ModelAttribute("directorAddDataTransferObject") AdminDirectorAddDTO adminDirectorAddDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("add-director")
                .addObject("profilePhotoUri", imageUrl);
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

    @GetMapping("/admin/director/delete/{id}")
    public ModelAndView deleteDirector(@PathVariable("id") String directorId) {
        this.directorService.deleteDirectorByDirectorId(directorId);

        return new ModelAndView("redirect:../../director/edit/all");
    }

    @GetMapping("/admin/director/edit/all")
    public ModelAndView allDirectors() {

        List<Director> directors = this.directorService.getAllDirectors();

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("all-directors-edit")
                .addObject("directors", directors)
                .addObject("profilePhotoUri", imageUrl);
    }

    @GetMapping("/admin/add/studio")
    public ModelAndView addStudioPage(@ModelAttribute("studioAddDataTransferObject") AdminAddStudioDTO adminAddStudioDTO) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("add-studio")
                .addObject("profilePhotoUri", imageUrl);
    }

    @GetMapping("/admin/studio/edit/all")
    public ModelAndView allStudios() {

        List<Studio> studios = this.studioService.getAllStudios();

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ModelAndView("all-studios-edit")
                .addObject("studios", studios)
                .addObject("profilePhotoUri", imageUrl);
    }

    @GetMapping("/admin/studio/delete/{id}")
    public ModelAndView deleteStudio(@PathVariable("id") String studioId) {
        this.studioService.deleteStudioById(studioId);

        return new ModelAndView("redirect:../../studio/edit/all");
    }

    @GetMapping("/admin/studio/edit/{id}")
    public ModelAndView editStudio(
            @ModelAttribute("studioAddDataTransferObject") AdminAddStudioDTO adminAddStudioDTO,
            @PathVariable("id") String studioId) {

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        Optional<Studio> studio = this.studioService.getStudioById(studioId);

        return new ModelAndView("edit-studio")
                .addObject("studio", studio.get())
                .addObject("profilePhotoUri", imageUrl);

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
