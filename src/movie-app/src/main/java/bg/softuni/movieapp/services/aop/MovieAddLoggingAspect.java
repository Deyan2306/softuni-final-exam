package bg.softuni.movieapp.services.aop;

import bg.softuni.movieapp.model.dto.admin.AdminMovieAddDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MovieAddLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(MovieAddLoggingAspect.class);

    @Pointcut("execution(public boolean addMovie(..))")
    public void addMoviePointcut() {
    }

    @AfterReturning(value = "addMoviePointcut()", returning = "result")
    public void logMovieAdded(JoinPoint joinPoint, boolean result) {
        Object[] args = joinPoint.getArgs();
        String movieTitle = null;

        if (args.length > 0 && args[0] instanceof AdminMovieAddDTO) {
            AdminMovieAddDTO adminMovieAddDTO = (AdminMovieAddDTO) args[0];
            movieTitle = adminMovieAddDTO.getTitle();
        }

        if (result) {
            logger.info("Movie '{}' added successfully.", movieTitle);
        } else {
            logger.warn("Failed to add movie '{}'.", movieTitle);
        }
    }

}
