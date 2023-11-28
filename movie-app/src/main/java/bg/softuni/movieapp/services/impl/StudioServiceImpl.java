package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.StudioRepository;
import bg.softuni.movieapp.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }
}
