package bg.softuni.movieapp.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminDirectorAddDTO {

    private String firstName;
    private String lastName;
    private String biography;
    private String birthDate;
    private String deathDate;
}
