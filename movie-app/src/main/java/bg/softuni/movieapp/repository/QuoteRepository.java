package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.objects.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}
