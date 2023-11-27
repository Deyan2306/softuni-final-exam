package bg.softuni.movieapp.model.entity.base;

import bg.softuni.movieapp.model.entity.Comment;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Article extends Commentable {

}
