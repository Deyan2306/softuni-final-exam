package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.UserRoleEntity;
import bg.softuni.movieapp.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    boolean existsUserRoleEntityByRole(UserRoleEnum userRole);

    Optional<UserRoleEntity> findByRole(UserRoleEnum userRole);
}
