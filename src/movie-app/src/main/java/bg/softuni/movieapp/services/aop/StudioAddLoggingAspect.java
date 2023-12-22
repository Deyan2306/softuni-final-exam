package bg.softuni.movieapp.services.aop;

import bg.softuni.movieapp.model.dto.admin.AdminAddStudioDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudioAddLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(StudioAddLoggingAspect.class);

    @Pointcut("execution(public boolean addStudio(..))")
    public void addStudioPointcut() {
    }

    @AfterReturning(value = "addStudioPointcut()", returning = "result")
    public void logStudioAdded(JoinPoint joinPoint, boolean result) {
        Object[] args = joinPoint.getArgs();
        String studioName = null;

        if (args.length > 0 && args[0] instanceof AdminAddStudioDTO) {
            AdminAddStudioDTO adminAddStudioDTO = (AdminAddStudioDTO) args[0];
            studioName = adminAddStudioDTO.getName();
        }

        if (result) {
            logger.info("Studio '{}' added successfully.", studioName);
        } else {
            logger.warn("Failed to add studio '{}'.", studioName);
        }
    }

}
