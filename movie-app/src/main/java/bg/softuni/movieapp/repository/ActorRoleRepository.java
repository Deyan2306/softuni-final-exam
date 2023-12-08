package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.ActorRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActorRoleRepository extends JpaRepository<ActorRole, UUID> {

    Optional<ActorRole> findActorRoleByCharacterFirstNameAndCharacterLastName(String firstName, String lastName);

}
