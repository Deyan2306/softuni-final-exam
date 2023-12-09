package bg.softuni.movieapp.model.dto.admin;

import bg.softuni.movieapp.model.entity.Actor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class AdminAddActorRoleDTO {

    @Size(min = 2)
    @NotBlank(message = "The role should have at least one name.")
    private String firstName;
    private String lastName;
    private String bio;
    private String birthDate;

    @NotBlank(message = "The role should have an actor, associated with it.")
    private String actorId;

}
