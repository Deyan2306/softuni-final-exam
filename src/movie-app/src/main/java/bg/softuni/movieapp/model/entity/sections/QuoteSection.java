package bg.softuni.movieapp.model.entity.sections;

import bg.softuni.movieapp.model.entity.base.BaseEntity;
import bg.softuni.movieapp.model.entity.objects.Quote;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quote_sections")
public class QuoteSection extends BaseEntity {

    @OneToMany(mappedBy = "quoteSection")
    private List<Quote> quotes;

}
