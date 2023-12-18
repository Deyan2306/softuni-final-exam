package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.objects.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetNumberOfComments() {

        when(commentRepository.count()).thenReturn(10L);

        int numberOfComments = commentService.getNumberOfComments();

        verify(commentRepository, times(1)).count();

        assertEquals(10, numberOfComments);
    }
}