package bg.softuni.movieapp.repository.objects;

import bg.softuni.movieapp.model.entity.objects.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
