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
@Table(name = "actor_roles")
public class ActorRole extends BaseEntity {
    private Actor realActor;
    private String characterFirstName;
    private String characterLastName;
    private LocalDate characterBirthDate;
    private String characterBio;
    private String characterPictureURI;
    private List<String> otherNames;
    private List<Comment> characterComments;
}
