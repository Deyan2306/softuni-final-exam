package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.entity.objects.Comment;
import bg.softuni.movieapp.model.events.UserRegistrationEvent;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.repository.UserRoleRepository;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static bg.softuni.movieapp.model.enums.UserRoleEnum.USER;
import static bg.softuni.movieapp.util.FilePathConstants.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher appEventPublisher;

    private final String profilePictureUploadURI;

    @Autowired
    public UserServiceImpl(@Value("${movieapp.upload.picture}") String profilePictureUploadURI,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder,
                           ApplicationEventPublisher appEventPublisher) {

        this.profilePictureUploadURI = profilePictureUploadURI;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appEventPublisher = appEventPublisher;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel == null) {
            return false;
        }

        String username = userRegisterBindingModel.getUsername();
        if (this.userRepository.findByUsername(username) != null) {
            return false;
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }

        UserRoleEntity userRole = userRoleRepository.findByRole(USER).orElseGet(() -> {
            UserRoleEntity newUserRole = new UserRoleEntity();
            newUserRole.setRole(USER);
            return userRoleRepository.save(newUserRole);
        });

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        userEntity.setEmail(userRegisterBindingModel.getEmail());
        userEntity.setAvatarPictureURI(DEFAULT_PROFILE_PICTURE_URI);
        userEntity.setRoles(List.of(userRole));

        this.userRepository.save(userEntity);

        String email = userEntity.getEmail();

        // Publish the event
        appEventPublisher.publishEvent(new UserRegistrationEvent(
                "UserService", username, email
        ));

        return true;
    }

    @Override
    public boolean changeProfileInformation(UserChangeInformationDTO userChangeInformationDTO) throws IOException {

        if (userChangeInformationDTO == null) {
            return false;
        }

        boolean changedSomething = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();

        UserEntity currentUser = this.userRepository.findByUsername(username);

        // Check for the location
        if (userChangeInformationDTO.getLocation() != null && !userChangeInformationDTO.getLocation().trim().isEmpty()) {
            currentUser.setLocation(userChangeInformationDTO.getLocation()); changedSomething = true;
        }

        // Check for the first and last names
        if (userChangeInformationDTO.getFirstName() != null && !userChangeInformationDTO.getFirstName().trim().isEmpty()) {
            currentUser.setFirstName(userChangeInformationDTO.getFirstName()); changedSomething = true;
        }

        if (userChangeInformationDTO.getLastName() != null && !userChangeInformationDTO.getLastName().trim().isEmpty()) {
            currentUser.setLastName(userChangeInformationDTO.getLastName()); changedSomething = true;
        }

        // Check the biography
        if (userChangeInformationDTO.getBiography() != null && !userChangeInformationDTO.getBiography().trim().isEmpty()) {
            currentUser.setBio(userChangeInformationDTO.getBiography()); changedSomething = true;
        }

        // Check for discord
        if (userChangeInformationDTO.getDiscord() != null && !userChangeInformationDTO.getDiscord().trim().isEmpty()) {
            currentUser.setDiscordUsername(userChangeInformationDTO.getDiscord()); changedSomething = true;
        }

        boolean changedProfilePicture = false;

        if (userChangeInformationDTO.getAvatar() != null) {
            MultipartFile file = userChangeInformationDTO.getAvatar();

            Path path = Path.of(USER_PICTURE_SAVE_URI);

            String id = String.valueOf(currentUser.getId());
            String fileName = id + ".png";
            Path targetPath = path.resolve(fileName);

            try {
                Files.write(targetPath, file.getBytes());
                currentUser.setAvatarPictureURI(DIRECTOR_PICTURE_SAVE_URI + fileName);
                changedProfilePicture = true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }


        userRepository.save(currentUser);

        return changedSomething || changedProfilePicture;
    }

    @Override
    public UserEntity getUserByUsername(String name) {
        return this.userRepository.findByUsername(name);
    }

    @Override
    public boolean isUserActive(UserEntity user) {
        LocalDateTime lastActivity = user.getLastActivityTimestamp();
        if (lastActivity != null) {
            // Define a threshold, e.g., 10 minutes
            LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
            return lastActivity.isAfter(threshold);
        }
        return false;
    }

    @Override
    public String getPhotoURIforUser(String username) {
        return this.userRepository.findByUsername(username).getAvatarPictureURI();
    }

    @Override
    public List<Comment> getLatestCreatedComments(UserEntity currentUser) {
        return currentUser.getCreatedComments().stream().limit(5).toList();
    }

    @Override
    public int getNumberOfUsers() {
        return (int) this.userRepository.count();
    }
}
