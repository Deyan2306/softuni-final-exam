package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter @Setter
public class AdminDirectorAddDTO {

    @Size(min = 2, message = "Director's first name should be at least 2 characters long.")
    private String firstName;

    @Size(min = 2, message = "Director's last name should be at least 2 characters long.")
    private String lastName;


    private String biography;

    @NotBlank(message = "The director's birth date should not be blank.")
    private String birthDate;
    private String deathDate;

    private MultipartFile directorPicture;
}
