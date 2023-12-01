package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminActorAddDTO {

    @Size(min = 2, message = "The first name should be at least 2 characters long.")
    private String firstName;

    @Size(min = 2, message = "The last name should be at least 2 characters long.")
    private String lastName;

    private String biography;

    @NotBlank(message = "The birth date should not be blank.")
    private String birthDate;
    private String deathDate;

    private MultipartFile actorPhoto;

    private int height;

    private String youtubeVideoId;
}
