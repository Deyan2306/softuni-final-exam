package bg.softuni.movieapp.model.dto.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminAddStudioDTO {

    private String name;
    private String info;
    private String establishedAt;
    private MultipartFile studioPicture;

}
