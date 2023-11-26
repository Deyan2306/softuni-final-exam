package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Watchable;
import bg.softuni.movieapp.model.enums.Language;
import bg.softuni.movieapp.model.enums.MovieGenre;
import bg.softuni.movieapp.model.enums.PGRating;
import jakarta.persistence.*;
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
public class Movie extends Watchable {

    private String title;
    private String titlePictureURI;
    private List<MovieGenre> genre;
    private PGRating pgRating;
    private String shortDescription;
    private List<Rating> ratings;

    @ManyToMany(mappedBy = "watchedMovies")
    private List<User> watchedBy;
    private String youtubeTrailerID;
    private LocalDate releaseDate;
    private Director director;
    private Long length;
    private List<Actor> cast;
    private String storyline;
    private List<Quote> quotes;
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private Language language;

}
