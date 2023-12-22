package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.events.UserRegistrationEvent;

public interface UserActivationService {

    void userRegistered(UserRegistrationEvent event);

    void cleanObsoleteActivationLinks();

    String createActivationCode(String userEmail);

}
