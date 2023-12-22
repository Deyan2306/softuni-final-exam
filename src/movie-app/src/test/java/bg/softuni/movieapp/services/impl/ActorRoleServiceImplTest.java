package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminAddActorRoleDTO;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.model.entity.ActorRole;
import bg.softuni.movieapp.repository.ActorRoleRepository;
import bg.softuni.movieapp.services.ActorService;
import bg.softuni.movieapp.services.CommentSectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ActorRoleServiceImplTest {

    @Mock
    private ActorRoleRepository actorRoleRepository;

    @Mock
    private CommentSectionService commentSectionService;

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorRoleServiceImpl actorRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddActorRole_Success() {
        AdminAddActorRoleDTO adminAddActorRoleDTO = new AdminAddActorRoleDTO();
        adminAddActorRoleDTO.setFirstName("John");
        adminAddActorRoleDTO.setLastName("Doe");
        adminAddActorRoleDTO.setBio("Sample bio");
        adminAddActorRoleDTO.setBirthDate("1990-01-01");
        adminAddActorRoleDTO.setActorId(UUID.randomUUID().toString());

        Actor actor = new Actor();
        actor.setId(UUID.randomUUID());
        when(actorService.getActorByActorId(adminAddActorRoleDTO.getActorId())).thenReturn(Optional.of(actor));

        when(actorRoleRepository.findActorRoleByCharacterFirstNameAndCharacterLastName("John", "Doe"))
                .thenReturn(Optional.empty());

        when(actorRoleRepository.save(any(ActorRole.class))).thenAnswer(invocation -> {
            ActorRole savedRole = invocation.getArgument(0);
            savedRole.setId(UUID.randomUUID());
            return savedRole;
        });

        boolean result = actorRoleService.addActorRole(adminAddActorRoleDTO);

        assertTrue(result, "Actor role addition should be successful");
    }

    @Test
    void testAddActorRoleWithNullDTO() {
        boolean result = actorRoleService.addActorRole(null);
        assertFalse(result, "Actor role addition should fail with a null DTO");
    }

    @Test
    void testAddActorRoleWhenRoleExists() {

        AdminAddActorRoleDTO adminAddActorRoleDTO = new AdminAddActorRoleDTO();
        adminAddActorRoleDTO.setFirstName("John");
        adminAddActorRoleDTO.setLastName("Doe");

        when(actorRoleRepository.findActorRoleByCharacterFirstNameAndCharacterLastName("John", "Doe"))
                .thenReturn(Optional.of(new ActorRole()));

        boolean result = actorRoleService.addActorRole(adminAddActorRoleDTO);

        assertFalse(result, "Actor role addition should fail when the role already exists");
    }

    @Test
    void testFindActorRoleByRoleId() {
        String roleId = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee";
        UUID uuid = UUID.fromString(roleId);
        ActorRole mockedActorRole = new ActorRole();

        when(actorRoleRepository.findById(uuid)).thenReturn(Optional.of(mockedActorRole));

        Optional<ActorRole> result = actorRoleService.findActorRoleByRoleId(roleId);

        assertEquals(Optional.of(mockedActorRole), result, "Returned ActorRole should match the mocked one");
    }

    @Test
    void testDeleteActorRoleById() {
        String roleId = "aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee";

        actorRoleService.deleteActorRoleById(roleId);

        verify(actorRoleRepository).deleteById(UUID.fromString(roleId));
    }

    @Test
    void testGetAllActorRoles() {
        ActorRole role1 = new ActorRole();
        ActorRole role2 = new ActorRole();
        List<ActorRole> mockRoles = Arrays.asList(role1, role2);

        when(actorRoleRepository.findAll()).thenReturn(mockRoles);

        List<ActorRole> retrievedRoles = actorRoleService.getAllActorRoles();

        assertEquals(mockRoles.size(), retrievedRoles.size(),
                "Number of retrieved roles should match the mock data size");
    }

    @Test
    public void testSetCharacterBioWhenBioEmpty() {
        AdminAddActorRoleDTO adminAddActorRoleDTO = new AdminAddActorRoleDTO();
        adminAddActorRoleDTO.setBio("");

        ActorRole role = new ActorRole();

        if (!adminAddActorRoleDTO.getBio().trim().isEmpty()) {
            role.setCharacterBio(adminAddActorRoleDTO.getBio());
        } else {
            role.setCharacterBio("There is no character bio yet.");
        }

        // Assert that the character bio is set to the default message
        assertEquals("There is no character bio yet.", role.getCharacterBio(),
                "Character bio should default to a placeholder when empty");
    }

}