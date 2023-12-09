package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminAddActorRoleDTO;
import bg.softuni.movieapp.model.entity.ActorRole;

import java.util.List;
import java.util.Optional;

public interface ActorRoleService {
    boolean addActorRole(AdminAddActorRoleDTO adminAddActorRoleDTO);

    Optional<ActorRole> findActorRoleByRoleId(String roleId);

    void deleteActorRoleById(String actorRoleId);

    List<ActorRole> getAllActorRoles();

}
