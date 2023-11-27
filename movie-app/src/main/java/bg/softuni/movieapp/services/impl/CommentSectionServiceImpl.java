package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.sections.CommentSectionRepository;
import bg.softuni.movieapp.services.CommentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentSectionServiceImpl implements CommentSectionService {

    private final LoggedUser loggedUser;
    private final CommentSectionRepository commentSectionRepository;

    @Autowired
    public CommentSectionServiceImpl(LoggedUser loggedUser, CommentSectionRepository commentSectionRepository) {
        this.loggedUser = loggedUser;
        this.commentSectionRepository = commentSectionRepository;
    }
}
