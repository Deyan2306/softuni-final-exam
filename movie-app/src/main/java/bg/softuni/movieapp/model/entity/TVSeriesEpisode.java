package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Watchable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv_series_episodes")
public class TVSeriesEpisode extends Watchable {

    private String title;
    private String summary;
    private LocalDate releaseDate;
    private String titleImageURI;
    private List<Rating> ratings;
    private Integer episode;
    private Integer season;

    // TODO: Make the relation
    private TVSeries series;

}
