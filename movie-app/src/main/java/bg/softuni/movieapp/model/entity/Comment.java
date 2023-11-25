package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.interfaces.Commentable;
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
@Table(name = "comments")
public class Comment extends BaseEntity {
    private User user;
    private String content;
    private List<User> liked;
    private List<User> disliked;
    private Commentable commentArticle;
    private LocalDate commentedAt;
}
