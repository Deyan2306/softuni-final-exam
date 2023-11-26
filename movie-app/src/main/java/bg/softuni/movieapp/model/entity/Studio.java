package bg.softuni.movieapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studios")
public class Studio extends Likeable {

    private String name;
    private LocalDate established;
    private List<Movie> movies;
    private String studioPictureURI;

}
