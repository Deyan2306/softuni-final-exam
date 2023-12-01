package bg.softuni.movieapp.model.dto.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminTVSeriesEpisodeDTO {

    private int episodeNumber;
    private int seasonNumber;
    private String releaseDate;
    private String title;
    private String summary;
    private MultipartFile titleImage;
}
