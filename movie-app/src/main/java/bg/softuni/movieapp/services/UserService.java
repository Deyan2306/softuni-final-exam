package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

}
