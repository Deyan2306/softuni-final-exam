package bg.softuni.movieapp.services.aop;

import bg.softuni.movieapp.model.dto.admin.AdminDirectorAddDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DirectorAddLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(DirectorAddLoggingAspect.class);

    @Pointcut("execution(public boolean addDirector(..))")
    public void addDirectorPointcut() {
    }

    @AfterReturning(value = "addDirectorPointcut()", returning = "result")
    public void logDirectorAdded(JoinPoint joinPoint, boolean result) {
        Object[] args = joinPoint.getArgs();
        String directorName = null;

        if (args.length > 0 && args[0] instanceof AdminDirectorAddDTO) {
            AdminDirectorAddDTO adminDirectorAddDTO = (AdminDirectorAddDTO) args[0];
            directorName = adminDirectorAddDTO.getFirstName() + " " + adminDirectorAddDTO.getLastName();
        }

        if (result) {
            logger.info("Director '{}' added successfully.", directorName);
        } else {
            logger.warn("Failed to add director '{}'.", directorName);
        }
    }

}
