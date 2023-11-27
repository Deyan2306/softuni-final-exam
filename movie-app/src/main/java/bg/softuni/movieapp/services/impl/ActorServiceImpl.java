package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.ActorRepository;
import bg.softuni.movieapp.services.ActorService;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final LoggedUser loggedUser;

    public ActorServiceImpl(ActorRepository actorRepository, LoggedUser loggedUser) {
        this.actorRepository = actorRepository;
        this.loggedUser = loggedUser;
    }
}
