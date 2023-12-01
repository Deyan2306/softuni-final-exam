package bg.softuni.movieapp.model.dto.admin;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AdminMovieAddDTO {
    private String genre;
    private String language;
    private int length;
    private String pgRating;
    private String releaseDate;
    private String summary;
    private String title;
    private MultipartFile titlePicture;
    private String youtubeTrailerID;
    private List<String> directorIDs;
    private List<String> actorIDs;

}
