package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.TVSeriesEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TVSeriesEpisodeRepository extends JpaRepository<TVSeriesEpisode, UUID> {

    Optional<TVSeriesEpisode> findTVSeriesEpisodeByEpisodeAndSeasonAndTitle(int episode, int season, String title);

}
