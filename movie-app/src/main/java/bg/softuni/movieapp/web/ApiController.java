package bg.softuni.movieapp.web;

import bg.softuni.movieapp.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/get")
public class ApiController {

    private final DirectorService directorService;

    @Autowired
    public ApiController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/directors")
    public ResponseEntity<?> getMovieGenres() {
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

}
