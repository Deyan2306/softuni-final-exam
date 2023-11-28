package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.sections.RatingSectionRepository;
import bg.softuni.movieapp.services.RatingSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingSectionServiceImpl implements RatingSectionService {

    private final RatingSectionRepository ratingSectionRepository;

    @Autowired
    public RatingSectionServiceImpl(RatingSectionRepository ratingSectionRepository) {
        this.ratingSectionRepository = ratingSectionRepository;
    }
}
