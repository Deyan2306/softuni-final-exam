package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.MovieRepository;
import bg.softuni.movieapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, LoggedUser loggedUser) {
        this.movieRepository = movieRepository;
        this.loggedUser = loggedUser;
    }
}
