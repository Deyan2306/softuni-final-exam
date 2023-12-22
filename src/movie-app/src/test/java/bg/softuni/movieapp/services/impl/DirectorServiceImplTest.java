package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminDirectorAddDTO;
import bg.softuni.movieapp.model.entity.Director;
import bg.softuni.movieapp.repository.DirectorRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectorServiceImplTest {

    @InjectMocks
    private DirectorServiceImpl directorService;

    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private CommentSectionService commentSectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDirectorSuccessful() throws IOException {

        AdminDirectorAddDTO directorDTO = new AdminDirectorAddDTO();
        directorDTO.setFirstName("John");
        directorDTO.setLastName("Doe");
        directorDTO.setBiography("Some bio");
        directorDTO.setBirthDate(LocalDate.now().toString());

        when(directorRepository.findDirectorByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.empty());
        doNothing().when(commentSectionService).createCommentSection(any());

        // Act
        boolean result = directorService.addDirector(directorDTO);

        // Assert
        assertTrue(result);
        verify(directorRepository, times(1)).save(any(Director.class));
    }

    @Test
    void testAddDirectorAlreadyExists() throws IOException {

        AdminDirectorAddDTO directorDTO = new AdminDirectorAddDTO();
        directorDTO.setFirstName("John");
        directorDTO.setLastName("Doe");
        directorDTO.setBiography("Some bio");
        directorDTO.setBirthDate(LocalDate.now().toString());

        when(directorRepository.findDirectorByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(new Director()));

        boolean result = directorService.addDirector(directorDTO);

        assertFalse(result);
        verify(directorRepository, never()).save(any(Director.class));
    }

    @Test
    void testDeleteDirectorByDirectorId() throws IOException {
        AdminDirectorAddDTO directorDTO = new AdminDirectorAddDTO();
        directorDTO.setFirstName("John");
        directorDTO.setLastName("Doe");
        directorDTO.setBiography("Some bio");
        directorDTO.setBirthDate(LocalDate.now().toString());

        Director director = new Director();
        director.setId(UUID.randomUUID());

        when(directorRepository.findDirectorByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(Optional.of(director));

        boolean result = directorService.addDirector(directorDTO);

        directorService.deleteDirectorByDirectorId(director.getId().toString());

        verify(directorRepository, times(1)).deleteById(director.getId());
    }

    @Test
    void testFindDirectorByFirstNameAndLastName() {

        Director expectedDirector = new Director();
        expectedDirector.setFirstName("John");
        expectedDirector.setLastName("Doe");
        when(directorRepository.findDirectorByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(Optional.of(expectedDirector));

        Optional<Director> result = directorService.findDirectorByFirstNameAndLastName("John", "Doe");

        assertEquals(expectedDirector, result.orElse(null),
                "Returned Director should match the expected Director");
    }

    @Test
    void testFindDirectorByDirectorID() {

        UUID directorId = UUID.randomUUID();

        Director expectedDirector = new Director();
        expectedDirector.setId(directorId);
        when(directorRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(expectedDirector));

        Optional<Director> result = directorService.findDirectorByDirectorID(directorId.toString());

        assertEquals(expectedDirector, result.orElse(null),
                "Returned Director should match the expected Director");
    }

    @Test
    void testGetAllDirectors() {

        Director director1 = new Director();
        director1.setId(UUID.randomUUID());
        Director director2 = new Director();
        director2.setId(UUID.randomUUID());

        List<Director> expectedDirectors = Arrays.asList(director1, director2);
        when(directorRepository.findAll()).thenReturn(expectedDirectors);

        List<Director> result = directorService.getAllDirectors();

        assertEquals(expectedDirectors.size(), result.size(),
                "Returned list should contain the same number of Directors");

        assertEquals(expectedDirectors.get(0).getId(), result.get(0).getId(),
                "IDs of the first Director should match");
        assertEquals(expectedDirectors.get(1).getId(), result.get(1).getId(),
                "IDs of the second Director should match");
    }

    @Test
    void testAddDirectorWithNull() throws IOException {
        boolean result = this.directorService.addDirector(null);

        Assertions.assertFalse(result);
    }
}