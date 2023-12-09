package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminAddStudioDTO;
import bg.softuni.movieapp.model.entity.Studio;

import java.util.List;
import java.util.Optional;

public interface StudioService {
    boolean addStudio(AdminAddStudioDTO adminAddStudioDTO);

    List<Studio> getAllStudios();

    void deleteStudioById(String studioId);

    Optional<Studio> getStudioById(String studioId);
}
