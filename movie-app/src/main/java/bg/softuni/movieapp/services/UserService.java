package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.model.entity.UserEntity;
import org.apache.catalina.User;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean changeProfileInformation(UserChangeInformationDTO userChangeInformationDTO) throws IOException;

    UserEntity getUserByUsername(String name);
    boolean isUserActive(UserEntity user);
}
