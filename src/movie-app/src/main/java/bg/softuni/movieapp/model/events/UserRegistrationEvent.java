package bg.softuni.movieapp.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegistrationEvent extends ApplicationEvent {

    private final String username;
    private final String userEmail;

    public UserRegistrationEvent(
            Object source,
            String username,
            String userEmail) {
        super(source);
        this.username = username;
        this.userEmail = userEmail;
    }
}
