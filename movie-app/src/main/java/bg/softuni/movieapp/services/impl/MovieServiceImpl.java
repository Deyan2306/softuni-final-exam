package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminMovieAddDTO;
import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.model.entity.ActorRole;
import bg.softuni.movieapp.model.entity.Movie;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.model.entity.sections.RatingSection;
import bg.softuni.movieapp.model.enums.LanguageEnum;
import bg.softuni.movieapp.model.enums.MovieGenreEnum;
import bg.softuni.movieapp.repository.MovieRepository;
import bg.softuni.movieapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CommentSectionService commentSectionService;
    private final RatingSectionService ratingSectionService;
    private final ActorRoleService actorRoleService;
    private final ActorService actorService;
    private final QuoteSectionService quoteSectionService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CommentSectionService commentSectionService, RatingSectionService ratingService, ActorRoleService actorRoleService, ActorService actorService, QuoteSectionService quoteSectionService) {
        this.movieRepository = movieRepository;
        this.commentSectionService = commentSectionService;
        this.ratingSectionService = ratingService;
        this.actorRoleService = actorRoleService;
        this.actorService = actorService;
        this.quoteSectionService = quoteSectionService;
    }

    @Override
    public int getNumberOfMovies() {
        return (int) this.movieRepository.count();
    }

    @Override
    public boolean addMovie(AdminMovieAddDTO adminMovieAddDTO) {

        if (adminMovieAddDTO == null) {
            return false;
        }

        String title = adminMovieAddDTO.getTitle();

        Optional<Movie> isExisting = this.movieRepository.findByTitle(title);

        if (isExisting.isPresent()) {
            return false;
        }

        Movie current = new Movie();

        current.setTitle(title);
        current.setLength(adminMovieAddDTO.getLength());

        // Add the genres
        if (!adminMovieAddDTO.getGenres().isEmpty()) {
            List<MovieGenreEnum> genres = new ArrayList<>();

            for (String genre : adminMovieAddDTO.getGenres()) {
                MovieGenreEnum currentGenre = MovieGenreEnum.valueOf(genre);
                genres.add(currentGenre);
            }

            current.setGenre(genres);
        }

        // Add the language
        if (!adminMovieAddDTO.getLanguage().trim().isEmpty()) {
            current.setLanguage(LanguageEnum.valueOf(adminMovieAddDTO.getLanguage()));
        }

        if (!adminMovieAddDTO.getSummary().trim().isEmpty()) {
            current.setSummary(adminMovieAddDTO.getSummary());
        } else {
            current.setSummary("There was no summary provided for this movie.");
        }

        if (!adminMovieAddDTO.getActorIDs().isEmpty()) {
            List<ActorRole> roles = new ArrayList<>();

//            for (String actorID : adminMovieAddDTO.getActorIDs()) {
//
//                ActorRole currentActorRole = new ActorRole();
//                currentActorRole.get
//
//                Optional<Actor> actorWithActorId = this.actorService.getActorByActorId(actorID);
//
//                if (actorWithActorId.isEmpty()) {
//                    // This actor is not existing D:
//                    continue;
//                } else {
//                    currentActorRole.setActor(actorWithActorId.get());
//                    currentActorRole.setMovieRolesPlayedAt();
//                }
//
//            }
            // TODO: FIX

            current.setMovieCast(roles);
        }



        CommentSection commentSection = new CommentSection();
        RatingSection ratingSection = new RatingSection();
        QuoteSection quoteSection = new QuoteSection();

        current.setCommentSection(commentSection);
        current.setRatingSection(ratingSection);
        current.setQuoteSection(quoteSection);

        this.commentSectionService.createCommentSection(commentSection);
        this.ratingSectionService.createRatingSection(ratingSection);
        this.quoteSectionService.createQuoteSection(quoteSection);

        this.movieRepository.save(current);

        return false;
    }
}
