package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.entity.objects.Comment;
import bg.softuni.movieapp.model.enums.UserRoleEnum;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.repository.UserRoleRepository;
import bg.softuni.movieapp.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Value("${movieapp.upload.picture}") String profilePictureUri;
    private UserService userServiceToTest;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleRepository mockUserRoleRepository;


    @BeforeEach
    void setUp() {

        userServiceToTest = new UserServiceImpl(
                profilePictureUri,
                mockUserRepository,
                mockUserRoleRepository,
                passwordEncoder
        );

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