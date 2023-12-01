package bg.softuni.movieapp.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

@Getter @Setter
public class UserRegisterBindingModel {

    @Size(min = 4, max = 100, message = "Username length must be at least 4 characters long")
    private String username;

    @Email
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Size(min = 8, max = 200, message = "Password must be at least 8 characters long")
    private String password;

    private String confirmPassword;

}
