package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.enums.UserRoleEnum;
import bg.softuni.movieapp.model.events.UserRegistrationEvent;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    //@Value("${movieapp.upload.picture}") String profilePictureUri;
    @InjectMocks
    private UserServiceImpl userServiceToTest;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ApplicationEventPublisher appEventPublisher;

    @Mock
    private UserRoleRepository mockUserRoleRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRegisterWithNullUser() {
        boolean result = this.userServiceToTest.register(null);

        Assertions.assertFalse(result);
    }

    @Test
    void testSuccessfulRegistration() {
        // Arrange
        UserRegisterBindingModel testRegister = createDummyUser();

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        when(mockUserRoleRepository.findByRole(UserRoleEnum.USER))
                .thenReturn(Optional.of(userRole));

        // Act
        boolean testRegistration = this.userServiceToTest.register(testRegister);

        // Assert
        Assertions.assertTrue(testRegistration);

        // Verify if publishEvent method is called with any UserRegistrationEvent
        verify(appEventPublisher, times(1)).publishEvent(any(UserRegistrationEvent.class));
    }

    @Test
    void testGetUserByUsername() {

        String username = "testUser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(userEntity);

        UserEntity retrievedUser = userServiceToTest.getUserByUsername(username);

        verify(mockUserRepository, times(1)).findByUsername(username);

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(username, retrievedUser.getUsername());
    }

    @Test
    void testGetNumberOfUsers() {

        when(mockUserRepository.count()).thenReturn(1L);

        Assertions.assertEquals(1, userServiceToTest.getNumberOfUsers());
    }

    private UserRegisterBindingModel createDummyUser() {
        UserRegisterBindingModel testUser = new UserRegisterBindingModel();
        testUser.setUsername("Deyan");
        testUser.setPassword("secretPassword1234");
        testUser.setConfirmPassword("secretPassword1234");
        testUser.setEmail("deyan.sirakov2006@abv.bg");

        return testUser;
    }
}