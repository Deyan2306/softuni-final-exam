package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminTVSeriesEpisodeDTO {

    @Size(min = 2, message = "TV Series episode title should be at least 2 characters long.")
    private String title;

    @Size(min = 20, message = "Movie's summary should be at least 20 characters long.")
    private String summary;

    @NotBlank(message = "You should point the episode number.")
    private int episodeNumber;

    @NotBlank(message = "You should point the season number.")
    private int seasonNumber;

    @NotBlank(message = "Release date must not be blank.")
    private String releaseDate;

    private MultipartFile titleImage;
}
