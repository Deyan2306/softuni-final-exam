package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.StudioRepository;
import bg.softuni.movieapp.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository, LoggedUser loggedUser) {
        this.studioRepository = studioRepository;
        this.loggedUser = loggedUser;
    }
}
