package bg.softuni.movieapp.init;

import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.enums.UserRoleEnum;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static bg.softuni.movieapp.util.FilePaths.DEFAULT_PROFILE_PICTURE_URI;

@Component
public class AdminProfileInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminUsername;
    private final String adminPassword;
    private final String adminEmail;

    @Autowired
    public AdminProfileInit(@Value("${movieapp.admin.username}") String adminUsername,
                            @Value("${movieapp.admin.password}") String adminPassword,
                            @Value("${movieapp.admin.email}") String adminEmail,
                            UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!this.userRoleRepository.existsUserRoleEntityByRole(UserRoleEnum.ADMIN)) {

            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);
            this.userRoleRepository.save(adminRole);

            UserEntity admin = new UserEntity();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setEmail(adminEmail);
            admin.setRoles(List.of(adminRole));
            admin.setDiscordUsername("Deyan2306");
            admin.setBio("This is the administrative account of the app.");
            admin.setAvatarPictureURI(DEFAULT_PROFILE_PICTURE_URI);

            this.userRepository.save(admin);
        }
    }
}
