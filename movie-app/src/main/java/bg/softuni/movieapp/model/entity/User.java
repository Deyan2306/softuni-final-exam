package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.BaseEntity;
import bg.softuni.movieapp.model.entity.objects.Comment;
import bg.softuni.movieapp.model.entity.objects.Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Size(min = 5, max = 100)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password; // TODO: Encrypt
    // TODO: Implement roles

    @Column(name = "avatar_picutre_uri")
    private String avatarPictureURI;

    @Size(min = 2, max = 100)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 100)
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 5)
    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Size(min = 2)
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> createdRatings;

    @Column(name = "discord_username")
    private String discordUsername;

    @OneToMany(mappedBy = "commentedBy")
    private List<Comment> createdComments;

    @ManyToMany
    @JoinTable(
            name = "user_upvoted_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "upvoted_comment_id")
    )
    private List<Comment> upVotedComments;

    @ManyToMany
    @JoinTable(
            name = "user_downvoted_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "downvoted_comment_id")
    )
    private List<Comment> downVotedComments;

    @ManyToMany
    @JoinTable(
            name = "user_watched_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> watchedMovies;

    @ManyToMany
    @JoinTable(
            name = "user_watched_tv_series",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tv_series_id")
    )
    private List<TVSeries> watchedTVSeries;

}
