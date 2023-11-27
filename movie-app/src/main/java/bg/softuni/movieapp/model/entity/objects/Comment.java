package bg.softuni.movieapp.model.entity.objects;

import bg.softuni.movieapp.model.entity.base.BaseEntity;
import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.model.entity.User;
import bg.softuni.movieapp.model.entity.base.Likeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Size(min = 10, max = 500)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentedBy;

    @Column(name = "commented_at", nullable = false)
    private LocalDate commentedAt;

    @ManyToOne
    @JoinColumn(name = "comment_section_id")
    private CommentSection commentSection;

    @ManyToMany(mappedBy = "upVotedComments")
    private List<User> upVotedBy;

    @ManyToMany(mappedBy = "downVotedComments")
    private List<User> downVotedBy;

}
