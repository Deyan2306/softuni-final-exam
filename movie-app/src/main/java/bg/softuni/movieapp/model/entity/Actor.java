package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.BaseEntity;
import bg.softuni.movieapp.model.entity.base.Likeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Actor extends Likeable {

    @Size(min = 2, max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = 2, max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @Column(name = "author_photo_uri", unique = true)
    private String authorPhotoURI;

    @Column(name = "personal_youtube_video_id")
    private String personalYoutubeVideoID;

    @Size(min = 20)
    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(name = "height")
    private Integer height;

    @OneToMany
    @JoinColumn(name = "actor_role_id")
    private List<ActorRole> roles;

}
