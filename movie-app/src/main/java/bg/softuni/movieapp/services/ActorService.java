package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;
import bg.softuni.movieapp.model.entity.Actor;

import java.util.Optional;

public interface ActorService {
    boolean addActor(AdminActorAddDTO adminActorAddDTO);

    int getNumberOfActors();

    Optional<Actor> getActorByActorId(String actorID);
}
