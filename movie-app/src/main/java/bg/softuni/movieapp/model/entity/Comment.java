package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.Likeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends Likeable {

    @OneToOne
    @Column(name = "commented_by", nullable = false)
    private User commentedBy;

    @Size(min = 10, max = 500)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne
    @Column(name = "article_from", nullable = false)
    private Article articleFrom;

    @Column(name = "commented_at", nullable = false)
    private LocalDate commentedAt;
}
