package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.model.entity.objects.Comment;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean changeProfileInformation(UserChangeInformationDTO userChangeInformationDTO) throws IOException;

    UserEntity getUserByUsername(String name);
    boolean isUserActive(UserEntity user);

    String getPhotoURIforUser(String username);

    List<Comment> getLatestCreatedComments(UserEntity currentUser);

    int getNumberOfUsers();
}
