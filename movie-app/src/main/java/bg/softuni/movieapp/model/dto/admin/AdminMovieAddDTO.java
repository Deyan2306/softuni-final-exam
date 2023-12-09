package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class AdminMovieAddDTO {

    @Size(min = 2, message = "Movie's title should be at least 2 characters long.")
    private String title;

    @Size(min = 20, message = "Movie's summary should be at least 20 characters long.")
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
