package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminMovieAddDTO;

public interface MovieService {
    int getNumberOfMovies();

    boolean addMovie(AdminMovieAddDTO adminMovieAddDTO);
}
