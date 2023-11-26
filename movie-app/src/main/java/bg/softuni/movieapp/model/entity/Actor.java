package bg.softuni.movieapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Actor extends Likeable {

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private LocalDate deathDate;
    private String authorPhotoURI;
    private String personalYoutubeVideoID;
    private String biography;
    private Integer height;
    private List<String> alternativeNames;
    private List<ActorRole> roles;
    private List<Comment> comments;

}
