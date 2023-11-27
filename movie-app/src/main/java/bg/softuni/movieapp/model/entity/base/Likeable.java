package bg.softuni.movieapp.model.entity.base;

import bg.softuni.movieapp.model.entity.User;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Likeable extends BaseEntity {

    @OneToMany
    @Column(name = "liked_by")
    private List<User> likedBy;

    @OneToMany
    @Column(name = "disliked_by")
    private List<User> dislikedBy;

    @OneToOne
    private CommentSection commentSection;

}
