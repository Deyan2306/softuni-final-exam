package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.interfaces.Reviewable;
import bg.softuni.movieapp.model.enums.Language;
import bg.softuni.movieapp.model.enums.PGRating;
import bg.softuni.movieapp.model.enums.TVSeriesGenre;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv_series")
public class TVSeries extends Likeable implements Reviewable {

    private String title;
    private String titlePictureURI;
    private List<TVSeriesEpisode> episodes;
    private List<TVSeriesGenre> genres;
    private PGRating pgRating;
    private String shortDescription;
    private List<Rating> ratings;
    private List<User> completedBy;
    private String youtubeTrailerID;
    private LocalDate releaseDate;
    private LocalDate endDate;
    private Director director;
    private Long length;
    private List<Actor> cast;
    private String storyline;
    private List<Quote> quotes;
    private Studio studio;
    private Language language;

}
