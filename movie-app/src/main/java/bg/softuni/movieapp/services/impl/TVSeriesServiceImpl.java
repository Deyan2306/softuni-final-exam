package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.TVSeriesEpisodeRepository;
import bg.softuni.movieapp.services.TVSeriesEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TVSeriesServiceImpl implements TVSeriesEpisodeService {

    private final TVSeriesEpisodeRepository tvSeriesEpisodeRepository;

    @Autowired
    public TVSeriesServiceImpl(TVSeriesEpisodeRepository tvSeriesEpisodeRepository) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
    }
}
