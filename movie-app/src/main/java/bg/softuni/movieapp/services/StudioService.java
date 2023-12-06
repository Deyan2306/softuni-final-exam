package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminAddStudioDTO;

public interface StudioService {
    boolean addStudio(AdminAddStudioDTO adminAddStudioDTO);
}
