package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class AdminMovieAddDTO {

    private String title;

    private String summary;

    private List<String> genres;
    private String language;

    @NotBlank(message = "The release date should not be blank.")
    private String releaseDate;

    private int length;
    private String pgRating;
    private MultipartFile titlePicture;
    private String youtubeTrailerID;
    private String studioId;
    private String directorIDs;
    private List<String> actorRoleIDs;

}
