package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article rated;

    @DecimalMin(value = "0.00")
    @DecimalMin(value = "10.00")
    @Column(name = "rating", nullable = false)
    private BigDecimal rating;

}
