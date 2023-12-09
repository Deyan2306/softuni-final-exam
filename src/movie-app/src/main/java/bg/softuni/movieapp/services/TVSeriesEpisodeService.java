package bg.softuni.movieapp.services;

import bg.softuni.movieapp.model.dto.admin.AdminTVSeriesEpisodeDTO;

public interface TVSeriesEpisodeService {
    int getNumberOfTVSeriesEpisodes();

    boolean addEpisode(AdminTVSeriesEpisodeDTO adminTVSeriesEpisodeDTO);

    void deleteTVSeriesEpisodeById(String tvSeriesEpisodeId);
}
