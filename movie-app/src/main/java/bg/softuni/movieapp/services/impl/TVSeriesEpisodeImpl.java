package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.TVSeriesEpisodeRepository;
import bg.softuni.movieapp.services.TVSeriesEpisodeService;
import org.springframework.stereotype.Service;

@Service
public class TVSeriesEpisodeImpl implements TVSeriesEpisodeService {

    private final TVSeriesEpisodeRepository tvSeriesEpisodeRepository;
    private final LoggedUser loggedUser;

    public TVSeriesEpisodeImpl(TVSeriesEpisodeRepository tvSeriesEpisodeRepository, LoggedUser loggedUser) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
        this.loggedUser = loggedUser;
    }
}
