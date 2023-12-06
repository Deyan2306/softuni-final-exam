package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.MovieRepository;
import bg.softuni.movieapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public int getNumberOfMovies() {
        return (int) this.movieRepository.count();
    }
}
