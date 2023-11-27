package bg.softuni.movieapp.model.entity;

import bg.softuni.movieapp.model.entity.base.Article;
import bg.softuni.movieapp.model.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    @OneToOne
    @Column(name = "rated_by", nullable = false)
    private User user;

    @OneToOne
    @Column(name = "article", nullable = false)
    private Article rated;

    @DecimalMin(value = "0.00")
    @DecimalMin(value = "10.00")
    @Column(name = "rating", nullable = false)
    private BigDecimal rating;

}
