package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminDirectorAddDTO;
import bg.softuni.movieapp.model.entity.Director;

import java.io.IOException;
import java.util.Optional;

public interface DirectorService {

    boolean addDirector(AdminDirectorAddDTO adminDirectorAddDTO) throws IOException;

    Object getAllDirectors();

    Optional<Director> findDirectorByDirectorID(String directorIDs);
}
