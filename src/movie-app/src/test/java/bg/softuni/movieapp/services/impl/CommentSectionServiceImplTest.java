package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.sections.CommentSectionRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentSectionServiceImplTest {

    @Mock
    private CommentSectionRepository commentSectionRepository;

    @InjectMocks
    private CommentSectionServiceImpl mockCommentSectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateCommentSection_Success() {

        CommentSection commentSection = new CommentSection();

        when(commentSectionRepository.save(any(CommentSection.class))).thenReturn(commentSection);

        mockCommentSectionService.createCommentSection(commentSection);

        verify(commentSectionRepository, times(1)).save(commentSection);
    }
}