package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.dto.admin.AdminMovieAddDTO;
import bg.softuni.movieapp.model.entity.ActorRole;
import bg.softuni.movieapp.model.entity.Director;
import bg.softuni.movieapp.model.entity.Movie;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.model.entity.sections.RatingSection;
import bg.softuni.movieapp.model.enums.LanguageEnum;
import bg.softuni.movieapp.model.enums.MovieGenreEnum;
import bg.softuni.movieapp.model.enums.PGRatingEnum;
import bg.softuni.movieapp.repository.MovieRepository;
import bg.softuni.movieapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static bg.softuni.movieapp.util.FilePaths.MOVIE_PICTURE_SAVE_URI;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CommentSectionService commentSectionService;
    private final RatingSectionService ratingSectionService;
    private final ActorRoleService actorRoleService;
    private final DirectorService directorService;
    private final QuoteSectionService quoteSectionService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CommentSectionService commentSectionService, RatingSectionService ratingService, ActorRoleService actorRoleService, DirectorService directorService, QuoteSectionService quoteSectionService) {
        this.movieRepository = movieRepository;
        this.commentSectionService = commentSectionService;
        this.ratingSectionService = ratingService;
        this.actorRoleService = actorRoleService;
        this.directorService = directorService;
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

        // Set the PG Rating
        if (!adminMovieAddDTO.getPgRating().trim().isEmpty()) {
            current.setPgRating(PGRatingEnum.valueOf(adminMovieAddDTO.getPgRating()));
        }

        if (!adminMovieAddDTO.getSummary().trim().isEmpty()) {
            current.setSummary(adminMovieAddDTO.getSummary());
        } else {
            current.setSummary("There was no summary provided for this movie.");
        }


        if (!adminMovieAddDTO.getActorRoleIDs().isEmpty()) {
            List<ActorRole> roles = new ArrayList<>();

            for (String roleId : adminMovieAddDTO.getActorRoleIDs()) {
                Optional<ActorRole> role = this.actorRoleService.findActorRoleByRoleId(roleId);
                role.ifPresent(roles::add);
            }

            current.setMovieCast(roles);
        }

        if (!adminMovieAddDTO.getDirectorIDs().trim().isEmpty()) {
            Optional<Director> director = this.directorService.findDirectorByDirectorID(adminMovieAddDTO.getDirectorIDs());
            director.ifPresent(current::setDirector);
        }


        if (!adminMovieAddDTO.getReleaseDate().trim().isEmpty()) {
            current.setReleaseDate(LocalDate.parse(adminMovieAddDTO.getReleaseDate()));
        }

        if (!adminMovieAddDTO.getYoutubeTrailerID().trim().isEmpty()) {
            current.setYoutubeTrailerID(adminMovieAddDTO.getYoutubeTrailerID());
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

        if (adminMovieAddDTO.getTitlePicture() != null) {

            MultipartFile file = adminMovieAddDTO.getTitlePicture();
            Movie currentMovie = this.movieRepository
                    .findByTitle(title).get();

            String id = String.valueOf(currentMovie.getId());

            Path path = Path.of(MOVIE_PICTURE_SAVE_URI);
            String fileName = id + ".png";
            Path targetPath = path.resolve(fileName);

            try {
                Files.write(targetPath, file.getBytes());
                currentMovie.setTitlePictureURI(MOVIE_PICTURE_SAVE_URI + fileName);
                this.movieRepository.save(currentMovie);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public void deleteMovieByMovieId(String movieID) {
        this.movieRepository.deleteById(UUID.fromString(movieID));
    }

    @Override
    public Page<Movie> getAllPagables(Pageable pageable) {
        return this.movieRepository.findAll(pageable);
    }

    @Override
    public Optional<Movie> findMovieById(String id) {
        return this.movieRepository.findById(UUID.fromString(id));
    }

    @Override
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }
}
