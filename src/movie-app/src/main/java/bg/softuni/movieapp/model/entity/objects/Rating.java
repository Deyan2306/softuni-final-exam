package bg.softuni.movieapp.model.entity.objects;

import bg.softuni.movieapp.model.entity.sections.RatingSection;
import bg.softuni.movieapp.model.entity.UserEntity;
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
    private UserEntity user;

    @DecimalMin(value = "0.00")
    @DecimalMin(value = "10.00")
    @Column(name = "rating", nullable = false)
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "rating_section_id")
    private RatingSection ratingSection;

}
