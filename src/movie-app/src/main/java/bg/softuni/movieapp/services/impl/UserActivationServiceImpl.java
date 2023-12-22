package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.UserActivationCodeEntity;
import bg.softuni.movieapp.model.events.UserRegistrationEvent;
import bg.softuni.movieapp.repository.UserActivationCodeRepository;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.services.EmailService;
import bg.softuni.movieapp.services.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationCodeRepository userActivationCodeRepository;


    @Autowired
    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @Override
    @EventListener(UserRegistrationEvent.class)
    public void userRegistered(UserRegistrationEvent event) {
        String activationCode = createActivationCode(event.getUserEmail());
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUsername(), activationCode);
    }

    @Override
    public void cleanObsoleteActivationLinks() {

    }

    @Override
    public String createActivationCode(String userEmail) {

        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();

        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail));

        userActivationCodeRepository.save(userActivationCodeEntity);

        return activationCode;
    }
    
    private static String generateActivationCode() {

        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randomIndex));
        }

        return activationCode.toString();
        
    }
}
