package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Watchable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv_series_episodes")
public class TVSeriesEpisode extends Watchable {

    @Size(min = 2)
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Size(min = 20, max = 1500)
    @Column(name = "summary", nullable = false, unique = true, columnDefinition = "TEXT")
    private String summary;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "title_image_uri", nullable = false, unique = true)
    private String titleImageURI;

    @Column(name = "episode_num", unique = true, nullable = false)
    private Integer episode;

    @Column(name = "season_num")
    private Integer season;

    @ManyToOne
    private TVSeries series;

}
