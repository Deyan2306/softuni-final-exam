package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Builder
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

    @Column(name = "avatar")
    private String avatarPictureURI; // Relative path to the avatar

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

    @Column(name = "discord_username")
    private String discordUsername;

    @OneToMany
    private List<Movie> watchedMovies;

    @OneToMany
    private List<TVSeries> watchedTVSeries;


}
