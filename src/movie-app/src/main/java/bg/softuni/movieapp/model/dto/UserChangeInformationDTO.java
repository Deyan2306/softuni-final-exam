package bg.softuni.movieapp.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class UserChangeInformationDTO {

    private String firstName;
    private String lastName;
    private String location;
    private String biography;
    private String discord;
    private MultipartFile avatar;

}
