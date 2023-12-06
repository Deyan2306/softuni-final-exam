package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminDirectorAddDTO;

import java.io.IOException;

public interface DirectorService {

    boolean addDirector(AdminDirectorAddDTO adminDirectorAddDTO) throws IOException;

}
