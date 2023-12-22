package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.Movie;
import bg.softuni.movieapp.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    public MovieServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindMovieById() {

        UUID movieId = UUID.randomUUID();
        Movie mockMovie = new Movie();
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));

        Optional<Movie> result = movieService.findMovieById(movieId.toString());

        assertEquals(mockMovie, result.orElse(null));
    }

    @Test
    void testGetAllMovies() {

        List<Movie> mockMovies = new ArrayList<>();
        mockMovies.add(new Movie());
        mockMovies.add(new Movie());

        when(movieRepository.findAll()).thenReturn(mockMovies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(mockMovies.size(), result.size());
    }
}