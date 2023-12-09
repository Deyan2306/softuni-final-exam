package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminAddActorRoleDTO;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.model.entity.ActorRole;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.ActorRoleRepository;
import bg.softuni.movieapp.services.ActorRoleService;
import bg.softuni.movieapp.services.ActorService;
import bg.softuni.movieapp.services.CommentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActorRoleServiceImpl implements ActorRoleService {

    private final ActorRoleRepository actorRoleRepository;
    private final CommentSectionService commentSectionService;
    private final ActorService actorService;

    @Autowired
    public ActorRoleServiceImpl(ActorRoleRepository actorRoleRepository, CommentSectionService commentSectionService, ActorService actorService) {
        this.actorRoleRepository = actorRoleRepository;
        this.commentSectionService = commentSectionService;
        this.actorService = actorService;
    }

    @Override
    public boolean addActorRole(AdminAddActorRoleDTO adminAddActorRoleDTO) {

        if (adminAddActorRoleDTO == null) {
            return false;
        }

        String firstName = adminAddActorRoleDTO.getFirstName();
        String lastName = adminAddActorRoleDTO.getLastName();

        if (this.actorRoleRepository.findActorRoleByCharacterFirstNameAndCharacterLastName(firstName, lastName).isPresent()) {
            return false;
        }

        ActorRole role = new ActorRole();
        CommentSection commentSection = new CommentSection();

        role.setCommentSection(commentSection);

        role.setCharacterFirstName(firstName);

        if (!lastName.trim().isEmpty()) {
            role.setCharacterLastName(lastName);
        }

        if (!adminAddActorRoleDTO.getBio().trim().isEmpty()) {
            role.setCharacterBio(adminAddActorRoleDTO.getBio());
        } else {
            role.setCharacterBio("There is no character bio yet.");
        }

        if (!adminAddActorRoleDTO.getBirthDate().trim().isEmpty()) {
            role.setCharacterBirthDate(LocalDate.parse(adminAddActorRoleDTO.getBirthDate()));
        }

        Optional<Actor> roleActor = this.actorService.getActorByActorId(adminAddActorRoleDTO.getActorId());
        roleActor.ifPresent(role::setActor);

        commentSectionService.createCommentSection(commentSection);
        this.actorRoleRepository.save(role);

        return true;
    }

    @Override
    public Optional<ActorRole> findActorRoleByRoleId(String roleId) {
        return this.actorRoleRepository.findById(UUID.fromString(roleId));
    }
}
