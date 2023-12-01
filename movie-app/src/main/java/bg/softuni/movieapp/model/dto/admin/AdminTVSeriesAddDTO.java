package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminTVSeriesAddDTO {

    @Size(min = 2, message = "TV Series title should be at least 2 characters long.")
    private String title;

    @Size(min = 20, message = "Movie's summary should be at least 20 characters long.")
    private String summary;


    @NotBlank(message = "TV series should have a start date.")
    private String startDate;
    private String endDate;

    private String genres;
    private String language;

    @Size(min = 0, message = "TV Series episode's length should be a positive number.")
    private int minutes;

    private String pgRating;

    private MultipartFile titlePictureUri;

    private String youtubeTrailerId;

}
