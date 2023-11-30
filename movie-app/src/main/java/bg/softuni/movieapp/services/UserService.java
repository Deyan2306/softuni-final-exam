package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;

import java.io.IOException;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean changeProfileInformation(UserChangeInformationDTO userChangeInformationDTO) throws IOException;
}
