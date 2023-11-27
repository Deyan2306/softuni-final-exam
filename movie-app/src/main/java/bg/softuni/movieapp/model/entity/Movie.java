package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Watchable;
import bg.softuni.movieapp.model.enums.Language;
import bg.softuni.movieapp.model.enums.MovieGenre;
import bg.softuni.movieapp.model.enums.PGRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie extends Watchable {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "title_picture_uri")
    private String titlePictureURI;

    @Enumerated(EnumType.STRING)
    private List<MovieGenre> genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "pg_rating", nullable = false)
    private PGRating pgRating;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    @OneToMany
    private List<Rating> ratings;

    @ManyToMany(mappedBy = "watchedMovies")
    private List<User> watchedBy;

    @Column(name = "youtube_trailer_id", nullable = false)
    private String youtubeTrailerID;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    private Director director;

    @Min(value = 0)
    @Column(nullable = false)
    private Integer length;

    @ManyToMany
    @JoinTable(
            name = "movie_actor_roles",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_role_id")
    )
    private List<ActorRole> movieCast;

    @ManyToOne
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private Language language;

}
