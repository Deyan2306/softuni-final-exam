package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.TVSeriesEpisodeRepository;
import bg.softuni.movieapp.services.TVSeriesEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TVSeriesEpisodeServiceImpl implements TVSeriesEpisodeService {

    private final TVSeriesEpisodeRepository tvSeriesEpisodeRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public TVSeriesEpisodeServiceImpl(TVSeriesEpisodeRepository tvSeriesEpisodeRepository, LoggedUser loggedUser) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
        this.loggedUser = loggedUser;
    }
}
