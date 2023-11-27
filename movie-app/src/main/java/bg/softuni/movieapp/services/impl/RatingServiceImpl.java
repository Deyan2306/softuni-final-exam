package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.RatingRepository;
import bg.softuni.movieapp.services.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final LoggedUser loggedUser;

    public RatingServiceImpl(RatingRepository ratingRepository, LoggedUser loggedUser) {
        this.ratingRepository = ratingRepository;
        this.loggedUser = loggedUser;
    }
}