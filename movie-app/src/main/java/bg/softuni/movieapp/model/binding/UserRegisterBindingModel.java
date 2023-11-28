package bg.softuni.movieapp.model.binding;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class UserRegisterBindingModel {

    @Length(min = 4, max = 100)
    private String username;

    @Email
    private String email;

    @Length(min = 8, max = 200)
    private String password;

    private String confirmPassword;

}
