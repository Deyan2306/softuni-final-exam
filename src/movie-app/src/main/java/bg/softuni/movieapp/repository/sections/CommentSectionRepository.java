package bg.softuni.movieapp.repository.sections;

import bg.softuni.movieapp.model.entity.sections.CommentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentSectionRepository extends JpaRepository<CommentSection, UUID> {
}
