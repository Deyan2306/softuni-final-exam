package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Likeable;
import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.model.entity.sections.RatingSection;
import bg.softuni.movieapp.model.enums.LanguageEnum;
import bg.softuni.movieapp.model.enums.MovieGenreEnum;
import bg.softuni.movieapp.model.enums.PGRatingEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie extends Likeable {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "title_picture_uri")
    private String titlePictureURI;

    @Enumerated(EnumType.STRING)
    private List<MovieGenreEnum> genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "pg_rating", nullable = false)
    private PGRatingEnum pgRating;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    @ManyToMany(mappedBy = "watchedMovies")
    private List<UserEntity> watchedBy;

    @Column(name = "youtube_trailer_id", nullable = false)
    private String youtubeTrailerID;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    private Director director;

    @Min(value = 0)
    @Column(nullable = false)
    private Integer length;

    @ManyToOne
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @OneToOne
    private QuoteSection quoteSection;

    @OneToOne
    private RatingSection ratingSection;

    @ManyToMany
    @JoinTable(
            name = "movie_actor_roles",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_role_id")
    )
    private List<ActorRole> movieCast;

}
