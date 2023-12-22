package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.enums.UserRoleEnum;
import bg.softuni.movieapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieAppUserDetailsServiceTest {

    private UserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new MovieAppUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("peshoshisharkata")
        );
    }

    @Test
    void testUserFoundException() {

        UserEntity testUserEntity = createTestUser();

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(testUserEntity);

        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        assertNotNull(userDetails);

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "The user is not admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "The user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEntity createTestUser() {

        UserEntity user = new UserEntity();
        user.setFirstName("Deyan");
        user.setUsername("deyan2306");
        user.setLastName("Sirakov");
        user.setBio("This is a test bio");
        user.setEmail("deyan.sirakov2006@abv.bg");

        UserRoleEntity userRole = new UserRoleEntity();
        UserRoleEntity adminRole = new UserRoleEntity();

        userRole.setRole(UserRoleEnum.USER);
        adminRole.setRole(UserRoleEnum.ADMIN);

        user.setRoles(List.of(userRole, adminRole));
        user.setPassword("topsecret");

        return user;
    }
}