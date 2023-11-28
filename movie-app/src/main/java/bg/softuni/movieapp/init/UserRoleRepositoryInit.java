package bg.softuni.movieapp.init;

import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static bg.softuni.movieapp.model.enums.UserRole.ADMIN;
import static bg.softuni.movieapp.model.enums.UserRole.USER;

@Component
public class UserRoleRepositoryInit implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleRepositoryInit(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        UserRoleEntity admin = new UserRoleEntity();
        admin.setRole(ADMIN);

        UserRoleEntity user = new UserRoleEntity();
        user.setRole(USER);

        userRoleRepository.save(admin);
        userRoleRepository.save(user);

    }
}
