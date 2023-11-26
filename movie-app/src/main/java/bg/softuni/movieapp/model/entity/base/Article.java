package bg.softuni.movieapp.model.entity.base;

import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article extends Commentable {

}
