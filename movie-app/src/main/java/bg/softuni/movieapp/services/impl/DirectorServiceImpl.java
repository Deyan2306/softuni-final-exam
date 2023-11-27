package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.DirectorRepository;
import bg.softuni.movieapp.services.DirectorService;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final LoggedUser loggedUser;

    public DirectorServiceImpl(DirectorRepository directorRepository, LoggedUser loggedUser) {
        this.directorRepository = directorRepository;
        this.loggedUser = loggedUser;
    }
}
