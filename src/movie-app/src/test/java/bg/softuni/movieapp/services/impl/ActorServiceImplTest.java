package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.repository.ActorRepository;
import bg.softuni.movieapp.repository.sections.CommentSectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ActorServiceImplTest {

    @InjectMocks
    private ActorServiceImpl actorService;

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private CommentSectionServiceImpl commentSectionService;

    @Mock
    private CommentSectionRepository commentSectionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteActorById() {

        UUID actorId = UUID.randomUUID();
        Actor actorToDelete = new Actor();
        when(actorRepository.findById(actorId)).thenReturn(Optional.of(actorToDelete));

        actorService.deleteActorById(actorId.toString());

        verify(actorRepository, times(1)).deleteById(actorId);
    }

    @Test
    public void testGetAllActors() {

        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        List<Actor> mockActors = Arrays.asList(actor1, actor2);
        when(actorRepository.findAll()).thenReturn(mockActors);

        List<Actor> retrievedActors = actorService.getAllActors();

        assertEquals(2, retrievedActors.size());
        assertEquals(actor1, retrievedActors.get(0));
        assertEquals(actor2, retrievedActors.get(1));

        verify(actorRepository, times(1)).findAll();
    }

    @Test
    public void testGetActorsBySearchAndSortReturnsNull() {
        Pageable pageable = Mockito.mock(Pageable.class);
        Mockito.when(pageable.getPageNumber()).thenReturn(1);
        Mockito.when(pageable.getPageSize()).thenReturn(10);

        Page<Actor> result = this.actorService.getActorsBySearchAndSort("", "", "", pageable);

        Assertions.assertNull(result);
    }

    @Test
    public void testGetActorByActorId_WhenActorExists() {

        UUID actorId = UUID.randomUUID();
        Actor mockActor = new Actor();
        mockActor.setId(actorId);
        when(actorRepository.findById(actorId)).thenReturn(Optional.of(mockActor));

        Optional<Actor> result = actorService.getActorByActorId(actorId.toString());

        assertEquals(actorId, result.orElseThrow().getId());
    }

    @Test
    public void testGetActorByActorId_WhenActorDoesNotExist() {

        UUID actorId = UUID.randomUUID();
        when(actorRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Optional<Actor> result = actorService.getActorByActorId(actorId.toString());

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetNumberOfActors() {
        when(actorRepository.count()).thenReturn(5L);

        int numberOfActors = actorService.getNumberOfActors();

        assertEquals(5, numberOfActors);
    }

    @Test
    public void testAddActorWithNull() {
        boolean result = this.actorService.addActor(null);

        Assertions.assertFalse(result);
    }
}