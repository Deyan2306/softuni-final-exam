package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DirectorRepository extends JpaRepository<Director, UUID> {

    Optional<Director> findDirectorByFirstNameAndLastName(String firstName, String lastName);

}
