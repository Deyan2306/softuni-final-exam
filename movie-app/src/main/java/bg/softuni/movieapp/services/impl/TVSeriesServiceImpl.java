package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.TVSeriesRepository;
import bg.softuni.movieapp.services.TVSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TVSeriesServiceImpl implements TVSeriesService {

    private final TVSeriesRepository tvSeriesRepository;

    @Autowired
    public TVSeriesServiceImpl(TVSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    @Override
    public int getNumberOfTVSeries() {
        return (int) this.tvSeriesRepository.count();
    }

    @Override
    public void deleteTVSeriesById(String tvSeriesId) {
        this.tvSeriesRepository.deleteById(UUID.fromString(tvSeriesId));
    }
}
