package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.interfaces.Reviewable;
import bg.softuni.movieapp.model.enums.Language;
import bg.softuni.movieapp.model.enums.MovieGenre;
import bg.softuni.movieapp.model.enums.PGRating;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie extends Likeable implements Reviewable {

    private String title;
    private String titlePictureURI;
    private List<MovieGenre> genre;
    private PGRating pgRating;
    private String shortDescription;
    private List<Rating> ratings;
    private Set<User> watchedBy;
    private String youtubeTrailerID;
    private LocalDate releaseDate;
    private Director director;
    private Long length;
    private List<Actor> cast;
    private String storyline;
    private List<Quote> quotes;
    private Studio studio;
    private Language language;

}
