package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;
import bg.softuni.movieapp.model.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    boolean addActor(AdminActorAddDTO adminActorAddDTO);

    int getNumberOfActors();

    Optional<Actor> getActorByActorId(String actorID);

    Page<Actor> getActorsBySearchAndSort(String searchName, String sort, String order, Pageable pageable);

    List<Actor> getAllActors();

    void deleteActorById(String actorId);
}
