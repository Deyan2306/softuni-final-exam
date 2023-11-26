package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studios")
public class Studio extends Article {

    @Size(min = 2, max = 100)
    @Column(name = "name")
    private String name;

    @Column(name = "established_at")
    private LocalDate established;

    @OneToMany
    private List<Movie> movies;

    @Column(name = "studio_picture_uri")
    private String studioPictureURI;

}