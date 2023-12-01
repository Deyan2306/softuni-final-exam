package bg.softuni.movieapp.model.dto.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminTVSeriesAddDTO {

    private String endDate;
    private String startDate;
    private String genres;
    private String language;
    private int minutes;
    private String pgRating;
    private String summary;
    private String title;
    private MultipartFile titlePictureUri;
    private String youtubeTrailerId;


}
