package bg.softuni.movieapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Likeable extends BaseEntity {

    @OneToMany
    @Column(name = "liked_by")
    private List<User> likedBy;

    @OneToMany
    @Column(name = "disliked_by")
    private List<User> dislikedBy;

}
