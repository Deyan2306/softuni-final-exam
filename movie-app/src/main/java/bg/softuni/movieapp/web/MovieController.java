package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.entity.Movie;
import bg.softuni.movieapp.services.MovieService;
import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ModelAndView viewMovies(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Movie> moviePage = movieService.getAllPagables(pageable);
        return new ModelAndView("movies")
                .addObject("moviePage", moviePage);
    }

    @GetMapping("/movies/{id}")
    public ModelAndView viewMovieDetails(@PathVariable("id") String id) {
        Movie movie = movieService.findMovieById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID"));

        return new ModelAndView("movie-details")
                .addObject("movie", movie);
    }

}
