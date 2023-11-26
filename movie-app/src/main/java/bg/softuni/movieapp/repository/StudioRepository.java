package bg.softuni.movieapp.repository;

import bg.softuni.movieapp.model.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudioRepository extends JpaRepository<Studio, UUID> {
}
