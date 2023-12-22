package bg.softuni.movieapp.services.aop;

import bg.softuni.movieapp.model.dto.admin.AdminActorAddDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActorAddLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ActorAddLoggingAspect.class);

    @Pointcut("execution(public boolean addActor(..))")
    public void addActorPointcut() {
    }

    @AfterReturning(value = "addActorPointcut()", returning = "result")
    public void logActorAdded(JoinPoint joinPoint, boolean result) {
        Object[] args = joinPoint.getArgs();
        String actorName = null;

        if (args.length > 0 && args[0] instanceof AdminActorAddDTO) {
            AdminActorAddDTO adminActorAddDTO = (AdminActorAddDTO) args[0];
            actorName = adminActorAddDTO.getFirstName() + " " + adminActorAddDTO.getLastName();
        }

        if (result) {
            logger.info("Actor '{}' added successfully.", actorName);
        } else {
            logger.warn("Failed to add actor '{}'.", actorName);
        }
    }

}
