package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Likeable;
import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.model.entity.sections.RatingSection;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv_series_episodes")
public class TVSeriesEpisode extends Likeable {

    @Size(min = 2)
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Size(min = 20, max = 1500)
    @Column(name = "summary", nullable = false, unique = true, columnDefinition = "TEXT")
    private String summary;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "episode_num", unique = true, nullable = false)
    private Integer episode;

    @Column(name = "season_num")
    private Integer season;

    @ManyToOne
    private TVSeries series;

    @Column(name = "title_image_uri", nullable = false, unique = true)
    private String titleImageURI;

    @OneToOne
    private QuoteSection quoteSection;

    @OneToOne
    private RatingSection ratingSection;


}
