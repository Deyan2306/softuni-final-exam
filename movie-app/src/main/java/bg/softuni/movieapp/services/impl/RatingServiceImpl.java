package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.objects.RatingRepository;
import bg.softuni.movieapp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public int getNumberOfRatings() {
        return (int) this.ratingRepository.findAll().stream().count();
    }
}
