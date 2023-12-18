package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.sections.RatingSection;
import bg.softuni.movieapp.repository.sections.RatingSectionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class RatingSectionServiceImplTest {

    @InjectMocks
    private RatingSectionServiceImpl ratingSectionService;

    @Mock
    private RatingSectionRepository ratingSectionRepository;

    public RatingSectionServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRatingSection() {

        RatingSection ratingSection = new RatingSection();

        ratingSectionService.createRatingSection(ratingSection);

        verify(ratingSectionRepository).save(ratingSection);
    }
}