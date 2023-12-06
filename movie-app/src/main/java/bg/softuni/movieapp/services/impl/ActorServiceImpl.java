package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.repository.ActorRepository;
import bg.softuni.movieapp.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public boolean addActor(AdminActorAddDTO adminActorAddDTO) {

        if (adminActorAddDTO == null) {
            return false;
        }

        final String firstName = adminActorAddDTO.getFirstName();
        final String lastName = adminActorAddDTO.getLastName();

        if (this.actorRepository.findActorByFirstNameAndLastName(firstName, lastName).isPresent()) {
            // This actor is already in the database
            return false;
        }

        Actor actor = new Actor();

        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        if (!adminActorAddDTO.getBiography().trim().isEmpty()) {
            actor.setBiography(adminActorAddDTO.getBiography());
        } else {
            actor.setBiography("There is no biography for this actor yet.");
        }

        actor.setBirthdate(LocalDate.parse(adminActorAddDTO.getBirthDate()));

        if (!adminActorAddDTO.getDeathDate().trim().isEmpty()) {
            actor.setDeathDate(LocalDate.parse(adminActorAddDTO.getDeathDate()));
        }

        if (adminActorAddDTO.getHeight() != 0) {
            actor.setHeight(adminActorAddDTO.getHeight());
        }

        if (!adminActorAddDTO.getYoutubeVideoId().trim().isEmpty()) {
            actor.setPersonalYoutubeVideoID(adminActorAddDTO.getYoutubeVideoId());
        }

        // TODO: Save the picture

        this.actorRepository.save(actor);

        return true;
    }

    @Override
    public int getNumberOfActors() {
        return (int) this.actorRepository.count();
    }
}
