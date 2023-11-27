package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Commentable;
import bg.softuni.movieapp.model.entity.base.Likeable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends Likeable {

    @Size(min = 10, max = 500)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentedBy;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonIgnoreProperties({"comments"})
    private Commentable commentFrom;

    @Column(name = "commented_at", nullable = false)
    private LocalDate commentedAt;
}
