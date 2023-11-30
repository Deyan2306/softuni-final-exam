package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.repository.UserRoleRepository;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static bg.softuni.movieapp.model.enums.UserRole.USER;
import static bg.softuni.movieapp.util.FilePaths.DEFAULT_PROFILE_PICTURE_URI;
import static bg.softuni.movieapp.util.FilePaths.PROFILE_PICTURE_NAME;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final String profilePictureUploadURI;

    @Autowired
    public UserServiceImpl(@Value("${movieapp.upload.picture}") String profilePictureUploadURI,
                           UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder) {

        this.profilePictureUploadURI = profilePictureUploadURI;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
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

        MultipartFile file = userChangeInformationDTO.getAvatar();
        boolean savedPicture = false;
        if (!file.isEmpty()) {
            savedPicture = saveProfilePicture(username, file, currentUser);
        }

        userRepository.save(currentUser);

        return changedSomething || savedPicture;
    }

    private boolean saveProfilePicture(String username, MultipartFile file, UserEntity currentUser) throws IOException {
        String folderPath =  profilePictureUploadURI + username + ".png";

        if (file.isEmpty()) {
            return false;
        }

        File dest = new File(folderPath);
        file.transferTo(dest);

        currentUser.setAvatarPictureURI(folderPath);
        return true;
    }

    private long getUploadedPicturesCount(String folderPath) throws IOException {
        return Files.list(Paths.get(folderPath)).count();
    }
}
