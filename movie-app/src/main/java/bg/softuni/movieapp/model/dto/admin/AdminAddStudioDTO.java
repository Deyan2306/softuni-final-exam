package bg.softuni.movieapp.model.dto.admin;

import org.springframework.web.multipart.MultipartFile;

public class AdminAddStudioDTO {

    private String name;
    private String info;
    private String establishedAt;
    private MultipartFile studioPicture;

}
