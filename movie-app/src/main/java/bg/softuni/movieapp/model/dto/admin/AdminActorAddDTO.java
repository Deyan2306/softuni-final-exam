package bg.softuni.movieapp.model.dto.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminActorAddDTO {
    private String firstName;
    private String lastName;
    private String biography;
    private String birthDate;
    private String deathDate;
    private MultipartFile actorPhoto;
    private int height;
    private String youtubeVideoId;
}
