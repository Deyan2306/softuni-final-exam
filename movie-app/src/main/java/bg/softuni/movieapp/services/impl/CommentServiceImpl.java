package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.CommentRepository;
import bg.softuni.movieapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, LoggedUser loggedUser) {
        this.commentRepository = commentRepository;
        this.loggedUser = loggedUser;
    }
}
