package bg.softuni.movieapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes")
public class Quote extends BaseEntity {
    private Movie quoteFor;
    private String content;
    private List<User> liked;
    private List<User> disliked;
}
