package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminMovieAddDTO;
import bg.softuni.movieapp.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
    int getNumberOfMovies();

    boolean addMovie(AdminMovieAddDTO adminMovieAddDTO);

    void deleteMovieByMovieId(String movieID);

    Page<Movie> getAllPagables(Pageable pageable);

    Optional<Movie> findMovieById(String id);

    List<Movie> getAllMovies();
}
