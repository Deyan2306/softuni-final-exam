package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.TVSeriesEpisodeRepository;
import bg.softuni.movieapp.services.TVSeriesEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TVSeriesEpisodeServiceImpl implements TVSeriesEpisodeService {

    private final TVSeriesEpisodeRepository tvSeriesEpisodeRepository;

    @Autowired
    public TVSeriesEpisodeServiceImpl(TVSeriesEpisodeRepository tvSeriesEpisodeRepository) {
        this.tvSeriesEpisodeRepository = tvSeriesEpisodeRepository;
    }

    @Override
    public int getNumberOfTVSeriesEpisodes() {
        return (int) this.tvSeriesEpisodeRepository.count();
    }
}
