package bg.softuni.movieapp.services.schedulers;

import bg.softuni.movieapp.services.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivationLinkCleanupScheduler {

    private final UserActivationService userActivationService;

    @Autowired
    public ActivationLinkCleanupScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    //@Scheduled(cron = "*/10 * * * * *")
    @Scheduled(fixedRate = 10_000, initialDelay = 10_000)
    public void cleanUp() {
        System.out.println("[" + LocalDateTime.now() + "]" + " Trigger clean-up of activation links");
        this.userActivationService.cleanObsoleteActivationLinks();
    }

}
