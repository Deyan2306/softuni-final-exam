package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Likeable;
import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.model.entity.sections.RatingSection;
import bg.softuni.movieapp.model.enums.Language;
import bg.softuni.movieapp.model.enums.PGRating;
import bg.softuni.movieapp.model.enums.TVSeriesGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv_series")
public class TVSeries extends Likeable {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "genres", nullable = false)
    private List<TVSeriesGenre> genres;

    @ManyToOne
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "pg_rating", nullable = false)
    private PGRating pgRating;

    @Size(min = 20, max = 1500)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    private Director director;

    @Min(value = 0)
    @Column(name = "length_in_minutes")
    private Integer lengthInMinutes;

    @OneToMany(mappedBy = "series")
    private List<TVSeriesEpisode> episodes;

    @Column(name = "title_picture_uri", unique = true)
    private String titlePictureURI;

    @Column(name = "youtube_trailer_id", nullable = false)
    private String youtubeTrailerID;

    @ManyToMany(mappedBy = "watchedTVSeries")
    private List<User> completedBy;

    @OneToOne
    private QuoteSection quoteSection;

    @OneToOne
    private RatingSection ratingSection;

    @ManyToMany
    @JoinTable(
            name = "tv_series_actor_roles",
            joinColumns = @JoinColumn(name = "tv_series_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_role_id")
    )
    private List<ActorRole> tvSeriesCast;

}
