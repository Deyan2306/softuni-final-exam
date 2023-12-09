package bg.softuni.movieapp.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class AdminAddStudioDTO {

    @Size(min = 2, message = "The studio's name should be at least 2 characters long.")
    private String name;

    private String info;

    @NotBlank(message = "The 'established at' date should not be blank.")
    private String establishedAt;

    private MultipartFile studioPicture;

}
