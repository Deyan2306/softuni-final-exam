package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.ActorRoleRepository;
import bg.softuni.movieapp.services.ActorRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorRoleServiceImpl implements ActorRoleService {

    private final ActorRoleRepository actorRoleRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public ActorRoleServiceImpl(ActorRoleRepository actorRoleRepository, LoggedUser loggedUser) {
        this.actorRoleRepository = actorRoleRepository;
        this.loggedUser = loggedUser;
    }
}
