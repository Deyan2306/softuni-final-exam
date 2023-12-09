package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.model.entity.Studio;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.ActorRepository;
import bg.softuni.movieapp.services.ActorService;
import bg.softuni.movieapp.services.CommentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static bg.softuni.movieapp.util.FilePaths.ACTOR_PICTURE_SAVE_URI;
import static bg.softuni.movieapp.util.FilePaths.STUDIO_PICTURE_SAVE_URI;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final CommentSectionService commentSectionService;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, CommentSectionService commentSectionService) {
        this.actorRepository = actorRepository;
        this.commentSectionService = commentSectionService;
    }

    @Override
    public boolean addActor(AdminActorAddDTO adminActorAddDTO) {

        if (adminActorAddDTO == null) {
            return false;
        }

        final String firstName = adminActorAddDTO.getFirstName();
        final String lastName = adminActorAddDTO.getLastName();

        if (this.actorRepository.findActorByFirstNameAndLastName(firstName, lastName).isPresent()) {
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

        CommentSection commentSection = new CommentSection();
        actor.setCommentSection(commentSection);

        this.commentSectionService.createCommentSection(commentSection);
        this.actorRepository.save(actor);

        if (adminActorAddDTO.getActorPhoto() != null) {

            MultipartFile file = adminActorAddDTO.getActorPhoto();
            Actor currentActor = this.actorRepository
                    .findActorByFirstNameAndLastName(firstName, lastName).get();

            String id = String.valueOf(currentActor.getId());

            Path path = Path.of(ACTOR_PICTURE_SAVE_URI);
            String fileName = id + ".png";
            Path targetPath = path.resolve(fileName);

            try {
                Files.write(targetPath, file.getBytes());
                currentActor.setActorPhotoURI(ACTOR_PICTURE_SAVE_URI + fileName);
                this.actorRepository.save(currentActor);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public int getNumberOfActors() {
        return (int) this.actorRepository.count();
    }

    @Override
    public Optional<Actor> getActorByActorId(String actorID) {
        return this.actorRepository.findById(UUID.fromString(actorID));
    }

    @Override
    public Page<Actor> getActorsBySearchAndSort(String searchName, String sort, String order, Pageable pageable) {
        return null;
    }

    @Override
    public List<Actor> getAllActors() {
        return this.actorRepository.findAll();
    }
}
