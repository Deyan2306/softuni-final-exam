package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.objects.QuoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuoteServiceImplTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteServiceImpl quoteService;

    public QuoteServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNumberOfQuotes() {

        when(quoteRepository.count()).thenReturn(5L);

        int numberOfQuotes = quoteService.getNumberOfQuotes();

        assertEquals(5, numberOfQuotes);
    }
}