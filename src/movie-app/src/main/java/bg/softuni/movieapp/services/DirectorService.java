package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminDirectorAddDTO;
import bg.softuni.movieapp.model.entity.Director;

import java.io.IOException;
import java.util.Optional;
import java.util.List;

public interface DirectorService {

    boolean addDirector(AdminDirectorAddDTO adminDirectorAddDTO) throws IOException;

    List<Director> getAllDirectors();

    Optional<Director> findDirectorByDirectorID(String directorIDs);

    void deleteDirectorByDirectorId(String directorId);

    Optional<Director> findDirectorByFirstNameAndLastName (String firstName, String lastName);
}
