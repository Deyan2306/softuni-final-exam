package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.enums.UserRole;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.repository.UserRoleRepository;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static bg.softuni.movieapp.model.enums.UserRole.USER;
import static bg.softuni.movieapp.util.FilePaths.DEFAULT_PROFILE_PICTURE_URI;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
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
}
