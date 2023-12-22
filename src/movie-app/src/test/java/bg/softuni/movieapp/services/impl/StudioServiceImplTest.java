package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminAddStudioDTO;
import bg.softuni.movieapp.model.entity.Studio;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.StudioRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudioServiceImplTest {

    @Mock
    private StudioRepository studioRepository;

    @Mock
    private CommentSectionService commentSectionService;

    @InjectMocks
    private StudioServiceImpl studioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudioSuccessful() {

        AdminAddStudioDTO adminAddStudioDTO = new AdminAddStudioDTO();
        adminAddStudioDTO.setName("Test Studio");
        adminAddStudioDTO.setInfo("Some information");
        adminAddStudioDTO.setEstablishedAt("2022-01-01");

        when(studioRepository.findByName("Test Studio")).thenReturn(Optional.empty());

        when(studioRepository.save(any(Studio.class))).thenAnswer(invocation -> {
            Studio savedStudio = invocation.getArgument(0);
            savedStudio.setId(UUID.randomUUID());
            return savedStudio;
        });
        doNothing().when(commentSectionService).createCommentSection(any(CommentSection.class));

        boolean result = studioService.addStudio(adminAddStudioDTO);

        assertTrue(result);
        verify(studioRepository, times(1)).findByName("Test Studio");
        verify(studioRepository, times(1)).save(any(Studio.class));
        verify(commentSectionService, times(1)).createCommentSection(any(CommentSection.class));
    }

    @Test
    void testAddStudioExistingStudio() {

        AdminAddStudioDTO adminAddStudioDTO = new AdminAddStudioDTO();
        adminAddStudioDTO.setName("Existing Studio");
        adminAddStudioDTO.setInfo("Some information");
        adminAddStudioDTO.setEstablishedAt("2022-01-01");

        Studio existingStudio = new Studio();
        when(studioRepository.findByName("Existing Studio")).thenReturn(Optional.of(existingStudio));

        boolean result = studioService.addStudio(adminAddStudioDTO);

        assertFalse(result);
        verify(studioRepository, times(1)).findByName("Existing Studio");
        verify(studioRepository, never()).save(any(Studio.class));
        verify(commentSectionService, never()).createCommentSection(any(CommentSection.class));
    }

    @Test
    void testGetAllStudiosEmptyList() {

        when(studioRepository.findAll()).thenReturn(Collections.emptyList());

        List<Studio> studios = studioService.getAllStudios();

        assertTrue(studios.isEmpty());
    }

    @Test
    void testGetAllStudiosNonEmptyList() {

        Studio studio1 = new Studio();
        studio1.setId(UUID.randomUUID());
        studio1.setName("Studio 1");

        Studio studio2 = new Studio();
        studio2.setId(UUID.randomUUID());
        studio2.setName("Studio 2");

        List<Studio> studios = Arrays.asList(studio1, studio2);

        when(studioRepository.findAll()).thenReturn(studios);

        List<Studio> retrievedStudios = studioService.getAllStudios();

        assertEquals(2, retrievedStudios.size());
        assertEquals("Studio 1", retrievedStudios.get(0).getName());
        assertEquals("Studio 2", retrievedStudios.get(1).getName());
    }

    @Test
    void testDeleteStudioByIdValidId() {

        UUID validId = UUID.randomUUID();
        Studio studioToDelete = new Studio();
        when(studioRepository.findById(validId)).thenReturn(Optional.of(studioToDelete));

        studioService.deleteStudioById(validId.toString());

        verify(studioRepository, times(1)).deleteById(validId);
    }

    @Test
    void testAddStudioNull() {
        boolean result = this.studioService.addStudio(null);

        Assertions.assertFalse(result);
    }

    @Test
    void testGetStudioByIdValidId() {

        UUID validId = UUID.randomUUID();

        Studio expectedStudio = new Studio();
        expectedStudio.setId(validId);
        expectedStudio.setName("Test Studio");

        when(studioRepository.findById(validId)).thenReturn(Optional.of(expectedStudio));

        Optional<Studio> result = studioService.getStudioById(validId.toString());

        assertTrue(result.isPresent());
        assertEquals(expectedStudio, result.get());
    }

    @Test
    void testGetStudioByIdInvalidId() {
        UUID invalidId = UUID.randomUUID();

        when(studioRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<Studio> result = studioService.getStudioById(invalidId.toString());

        assertTrue(result.isEmpty());
    }
}