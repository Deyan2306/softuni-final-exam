package bg.softuni.movieapp.services.impl;

// TODO: Use spring security

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter @Setter
public class LoggedUser {

    private String username;
    private boolean isLogged;

}
