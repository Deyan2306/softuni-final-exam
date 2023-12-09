package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminAddStudioDTO;
import bg.softuni.movieapp.model.entity.Studio;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.StudioRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import bg.softuni.movieapp.services.RatingService;
import bg.softuni.movieapp.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static bg.softuni.movieapp.util.FilePaths.STUDIO_PICTURE_SAVE_URI;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final CommentSectionService commentSectionService;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository, CommentSectionService commentSectionService, RatingService ratingService) {
        this.studioRepository = studioRepository;
        this.commentSectionService = commentSectionService;
    }

    @Override
    public boolean addStudio(AdminAddStudioDTO adminAddStudioDTO) {

        if (adminAddStudioDTO == null) {
            return false;
        }

        String name = adminAddStudioDTO.getName();

        Optional<Studio> existingStudio = this.studioRepository.findByName(name);

        if (existingStudio.isPresent()) {
           return false;
        }

        Studio studio = new Studio();
        CommentSection commentSection = new CommentSection();

        studio.setName(name);

        if (!adminAddStudioDTO.getInfo().trim().isEmpty()) {
            studio.setInfo(adminAddStudioDTO.getInfo());
        } else {
            studio.setInfo("No information about the studio is provided.");
        }

        if (!adminAddStudioDTO.getEstablishedAt().trim().isEmpty()) {
            studio.setEstablished(LocalDate.parse(adminAddStudioDTO.getEstablishedAt()));
        }

        studio.setCommentSection(commentSection);

        this.commentSectionService.createCommentSection(commentSection);
        this.studioRepository.save(studio);

        if (adminAddStudioDTO.getStudioPicture() != null) {

            MultipartFile file = adminAddStudioDTO.getStudioPicture();
            Studio currentDirector = this.studioRepository
                    .findByName(name).get();

            String id = String.valueOf(currentDirector.getId());

            Path path = Path.of(STUDIO_PICTURE_SAVE_URI);
            String fileName = id + ".png";
            Path targetPath = path.resolve(fileName);

            try {
                Files.createDirectories(targetPath.getParent());
                Files.write(targetPath, file.getBytes());
                currentDirector.setStudioPictureURI(STUDIO_PICTURE_SAVE_URI + fileName);
                this.studioRepository.save(currentDirector);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Studio> getAllStudios() {
        return this.studioRepository.findAll();
    }

    @Override
    public void deleteStudioById(String studioId) {
        this.studioRepository.deleteById(UUID.fromString(studioId));
    }
}
