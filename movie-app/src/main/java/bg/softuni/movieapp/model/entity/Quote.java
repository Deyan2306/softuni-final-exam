package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Likeable;
import bg.softuni.movieapp.model.entity.base.Watchable;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes")
public class Quote extends Likeable {

    @ManyToOne
    @JoinColumn(name = "watchable_id")
    private Watchable quoteFrom;

    @Column(columnDefinition = "TEXT")
    private String content;
}
