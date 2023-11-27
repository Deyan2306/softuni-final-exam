package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.QuoteRepository;
import bg.softuni.movieapp.services.QuoteService;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final LoggedUser loggedUser;

    public QuoteServiceImpl(QuoteRepository quoteRepository, LoggedUser loggedUser) {
        this.quoteRepository = quoteRepository;
        this.loggedUser = loggedUser;
    }
}
