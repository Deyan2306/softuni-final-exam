package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.sections.CommentSection;
import bg.softuni.movieapp.repository.sections.CommentSectionRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentSectionServiceImpl implements CommentSectionService {

    private final CommentSectionRepository commentSectionRepository;

    @Autowired
    public CommentSectionServiceImpl(CommentSectionRepository commentSectionRepository) {
        this.commentSectionRepository = commentSectionRepository;
    }

    @Override
    public void createCommentSection(CommentSection commentSection) {
        this.commentSectionRepository.save(commentSection);
    }
}
