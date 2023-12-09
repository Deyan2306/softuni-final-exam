package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.objects.CommentRepository;
import bg.softuni.movieapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public int getNumberOfComments() {
        return (int) this.commentRepository.count();
    }
}
