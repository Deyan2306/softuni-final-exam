package bg.softuni.movieapp.services;

public interface EmailService {

    void sendRegistrationEmail(String userEmail, String userName, String activationCode);

}
