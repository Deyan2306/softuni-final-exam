package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminDirectorAddDTO;
import bg.softuni.movieapp.model.entity.Director;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.DirectorRepository;
import bg.softuni.movieapp.repository.sections.CommentSectionRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import bg.softuni.movieapp.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static bg.softuni.movieapp.util.FilePaths.DIRECTOR_PICTURE_SAVE_URI;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final CommentSectionService commentSectionService;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository, CommentSectionService commentSectionService) {
        this.directorRepository = directorRepository;
        this.commentSectionService = commentSectionService;
    }

    @Override
    public boolean addDirector(AdminDirectorAddDTO adminDirectorAddDTO) throws IOException {

        if (adminDirectorAddDTO == null) {
            return false;
        }

        String firstName = adminDirectorAddDTO.getFirstName();
        String lastName = adminDirectorAddDTO.getLastName();

        // Check if the director already exists in the database
        if (this.directorRepository.findDirectorByFirstNameAndLastName(firstName, lastName).isPresent()) {
            return false;
        }

        Director director = new Director();

        director.setFirstName(firstName);
        director.setLastName(lastName);

        if (!adminDirectorAddDTO.getBiography().trim().isEmpty()) {
            director.setBio(adminDirectorAddDTO.getBiography());
        } else {
            director.setBio("No biography provided for this director");
        }

        director.setBirthDate(LocalDate.parse(adminDirectorAddDTO.getBirthDate()));

        if (!adminDirectorAddDTO.getDeathDate().trim().isEmpty()) {
            director.setDeathDate(LocalDate.parse(adminDirectorAddDTO.getDeathDate()));
        }

        CommentSection currentDirectorCommentSection = new CommentSection();
        director.setCommentSection(currentDirectorCommentSection);

        this.commentSectionService.createCommentSection(currentDirectorCommentSection);
        this.directorRepository.save(director);

        if (adminDirectorAddDTO.getDirectorPicture() != null) {

            MultipartFile file = adminDirectorAddDTO.getDirectorPicture();
            Director currentDirector = this.directorRepository
                    .findDirectorByFirstNameAndLastName(firstName, lastName).get();

            String id = String.valueOf(currentDirector.getId());

            Path path = Path.of(DIRECTOR_PICTURE_SAVE_URI);
            String fileName = id + ".png";
            Path targetPath = path.resolve(fileName);

            try {
                Files.write(targetPath, file.getBytes());
                currentDirector.setPictureUri(DIRECTOR_PICTURE_SAVE_URI + fileName);
                this.directorRepository.save(currentDirector);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public Object getAllDirectors() {
        return this.directorRepository.findAll();
    }
}
