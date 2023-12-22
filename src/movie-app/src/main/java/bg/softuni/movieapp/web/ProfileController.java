package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.dto.UserChangeInformationDTO;
import bg.softuni.movieapp.model.entity.UserEntity;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static bg.softuni.movieapp.util.FilePathConstants.DEFAULT_PROFILE_PICTURE_URI;
import static bg.softuni.movieapp.util.FilePathConstants.USER_PICTURE_GET_URI;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView myProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals("anonymousUser")) {
            return new ModelAndView("redirect:/");
        }

        UserEntity currentUser = userService.getUserByUsername(authentication.getName());
        boolean isUserActive = userService.isUserActive(currentUser);

        String imageUrl = this.userService.getPhotoURIforUser(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!imageUrl.equals(DEFAULT_PROFILE_PICTURE_URI)) {
            imageUrl = USER_PICTURE_GET_URI + this.userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId() + ".png";
        }

        return new ModelAndView("profile")
                .addObject("profilePicture", imageUrl)
                .addObject("user", currentUser)
                .addObject("isActive", isUserActive);
    }

    @GetMapping("/profile/movies")
    public ModelAndView myWatchedMovies() {
        return new ModelAndView("watched_movies");
    }

    @GetMapping("/profile/tv-series")
    public ModelAndView myWatchedTVSeries() {
        return new ModelAndView("not-constructed");
    }

    @GetMapping("profile/settings")
    public ModelAndView myProfileSettings() {
        return new ModelAndView("profile_settings");
    }

    @PostMapping("profile/settings")
    public ModelAndView savedProfileSettings(UserChangeInformationDTO userChangeInformationDTO,
                                             @RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {

        userChangeInformationDTO.setAvatar(profilePicture);
        boolean successful = this.userService.changeProfileInformation(userChangeInformationDTO);

        if (!successful) {
            return new ModelAndView("profile_settings").addObject("has_validation_errors", true);
        }

        return new ModelAndView("redirect:../home");
    }
}
