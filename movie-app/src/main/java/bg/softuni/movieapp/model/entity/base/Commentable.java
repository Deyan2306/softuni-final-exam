package bg.softuni.movieapp.model.entity.base;

import bg.softuni.movieapp.model.entity.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Commentable extends Likeable {

    @OneToMany(mappedBy = "commentFrom", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
