package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Watchable;
import bg.softuni.movieapp.model.enums.Language;
import bg.softuni.movieapp.model.enums.PGRating;
import bg.softuni.movieapp.model.enums.TVSeriesGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv_series")
public class TVSeries extends Watchable {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "title_picture_uri", unique = true)
    private String titlePictureURI;

    @OneToMany(mappedBy = "series")
    private List<TVSeriesEpisode> episodes;

    @Enumerated(EnumType.STRING)
    @Column(name = "genres", nullable = false)
    private List<TVSeriesGenre> genres;

    @Enumerated(EnumType.STRING)
    @Column(name = "pg_rating", nullable = false)
    private PGRating pgRating;

    @Size(min = 20, max = 1500)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    @OneToMany
    private List<Rating> ratings;

    @ManyToMany(mappedBy = "watchedTVSeries")
    private List<User> completedBy;

    @Column(name = "youtube_trailer_id", nullable = false)
    private String youtubeTrailerID;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    private Director director;

    @Min(value = 0)
    @Column(name = "length_in_minutes")
    private Integer lengthInMinutes;

    private List<Actor> cast;

    @OneToMany
    private List<Quote> quotes;

    @ManyToOne
    private Studio studio;

    @Enumerated(EnumType.STRING)
    private Language language;

}
