package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminTVSeriesEpisodeDTO;
import bg.softuni.movieapp.model.entity.TVSeriesEpisode;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.TVSeriesEpisodeRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import bg.softuni.movieapp.services.TVSeriesEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static bg.softuni.movieapp.util.FilePathConstants.TV_SERIES_EPISODE_PICTURE_SAVE_URI;

@Service
public class TVSeriesEpisodeServiceImpl implements TVSeriesEpisodeService {

    private final TVSeriesEpisodeRepository tvSeriesEpisodeRepository;
    private final CommentSectionService commentSectionService;

    @Autowired
    public TVSeriesEpisodeServiceImpl(TVSeriesEpisodeRepository tvSeriesEpisodeRepository, CommentSectionService commentSectionService) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
        this.commentSectionService = commentSectionService;
    }

    @Override
    public int getNumberOfTVSeriesEpisodes() {
        return (int) this.tvSeriesEpisodeRepository.count();
    }

    @Override
    public boolean addEpisode(AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO) {

        if (adminTVSeriesEpisodeDTO == null) {
            return false;
        }

        int episodeNumber = adminTVSeriesEpisodeDTO.getEpisodeNumber();
        int seasonNumber = adminTVSeriesEpisodeDTO.getSeasonNumber();
        String title = adminTVSeriesEpisodeDTO.getTitle();

        Optional<TVSeriesEpisode> isEpisodeExisting = this.tvSeriesEpisodeRepository
                .findTVSeriesEpisodeByEpisodeAndSeasonAndTitle(episodeNumber, seasonNumber, title);

        if (isEpisodeExisting.isPresent()) {
            return false;
        }

        TVSeriesEpisode current = new TVSeriesEpisode();

        current.setTitle(title);
        current.setEpisode(episodeNumber);
        current.setSeason(seasonNumber);

        if (!adminTVSeriesEpisodeDTO.getSummary().trim().isEmpty()) {
            current.setSummary(adminTVSeriesEpisodeDTO.getSummary());
        } else {
            current.setSummary("There is no summary for this episode yet");
        }

        if (!adminTVSeriesEpisodeDTO.getReleaseDate().trim().isEmpty()) {
            current.setReleaseDate(LocalDate.parse(adminTVSeriesEpisodeDTO.getReleaseDate()));
        }

        CommentSection commentSection = new CommentSection();
        current.setCommentSection(commentSection);

        commentSectionService.createCommentSection(commentSection);
        tvSeriesEpisodeRepository.save(current);

        if (adminTVSeriesEpisodeDTO.getTitleImage() != null) {

            MultipartFile file = adminTVSeriesEpisodeDTO.getTitleImage();
            TVSeriesEpisode currentTVSeriesEpisode = this.tvSeriesEpisodeRepository
                    .findTVSeriesEpisodeByEpisodeAndSeasonAndTitle(episodeNumber, seasonNumber, title).get();

            String id = String.valueOf(currentTVSeriesEpisode.getId());

            Path path = Path.of(TV_SERIES_EPISODE_PICTURE_SAVE_URI);
            String fileName = id + ".png";
            Path targetPath = path.resolve(fileName);

            try {
                Files.write(targetPath, file.getBytes());
                currentTVSeriesEpisode.setTitleImageURI(TV_SERIES_EPISODE_PICTURE_SAVE_URI + fileName);
                this.tvSeriesEpisodeRepository.save(currentTVSeriesEpisode);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Override
    public void deleteTVSeriesEpisodeById(String tvSeriesEpisodeId) {
        this.tvSeriesEpisodeRepository.deleteById(UUID.fromString(tvSeriesEpisodeId));
    }
}
