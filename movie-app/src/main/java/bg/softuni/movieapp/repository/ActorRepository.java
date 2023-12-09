package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActorRepository extends JpaRepository<Actor, UUID> {

    Optional<Actor> findActorByFirstNameAndLastName(String firstName, String lastName);

    List<Actor> findAllByFirstNameNotNull();

}
