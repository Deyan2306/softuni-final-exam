package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Commentable;
import bg.softuni.movieapp.model.entity.base.Watchable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor_roles")
public class ActorRole extends Commentable {

    @ManyToOne
    private Actor actor;

    @ManyToMany(mappedBy = "movieCast")
    private List<Movie> movieRolesPlayedAt;

    @ManyToMany(mappedBy = "tvSeriesCast")
    private List<TVSeries> tvSeriesPlayedAt;

    @Size(min = 2, max = 100)
    @Column(name = "character_first_name", nullable = false)
    private String characterFirstName;

    @Size(min = 2, max = 100)
    @Column(name = "character_last_name")
    private String characterLastName;

    @Column(name = "character_birth_date")
    private LocalDate characterBirthDate;

    @Size(min = 2, max = 100)
    @Column(name = "character_bio", columnDefinition = "TEXT")
    private String characterBio;



}
