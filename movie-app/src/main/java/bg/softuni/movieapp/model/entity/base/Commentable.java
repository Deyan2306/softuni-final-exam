package bg.softuni.movieapp.model.entity.base;

import bg.softuni.movieapp.model.entity.Comment;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Commentable extends Likeable {

    @OneToMany
    private List<Comment> comments;

}
