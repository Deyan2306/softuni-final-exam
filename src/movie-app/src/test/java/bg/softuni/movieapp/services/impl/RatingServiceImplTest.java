package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.objects.Rating;
import bg.softuni.movieapp.repository.objects.RatingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetNumberOfRatings() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating());
        ratings.add(new Rating());
        ratings.add(new Rating());

        when(ratingRepository.findAll()).thenReturn(ratings);

        int numberOfRatings = ratingService.getNumberOfRatings();

        verify(ratingRepository, times(1)).findAll();

        assertEquals(3, numberOfRatings);
    }
}