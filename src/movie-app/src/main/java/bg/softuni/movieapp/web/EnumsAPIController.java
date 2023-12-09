package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.enums.MovieGenreEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/enums")
public class EnumsAPIController {

    @GetMapping("/movie-genres")
    public ResponseEntity<?> getMovieGenres() {
        return ResponseEntity.ok(MovieGenreEnum.values());
    }

}
