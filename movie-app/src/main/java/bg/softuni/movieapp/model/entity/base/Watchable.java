package bg.softuni.movieapp.model.entity.base;

import bg.softuni.movieapp.model.entity.Quote;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Watchable extends Article {

    @OneToMany(mappedBy = "quoteFrom", cascade = CascadeType.ALL)
    private List<Quote> quotes;

}
