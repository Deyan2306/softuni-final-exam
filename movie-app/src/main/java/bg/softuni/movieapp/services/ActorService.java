package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;

public interface ActorService {
    boolean addActor(AdminActorAddDTO adminActorAddDTO);

    int getNumberOfActors();
}
