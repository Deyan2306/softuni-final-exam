package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.repository.sections.QuoteSectionRepository;
import bg.softuni.movieapp.services.QuoteSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteSectionServiceImpl implements QuoteSectionService {

    private final LoggedUser loggedUser;
    private final QuoteSectionRepository quoteSectionRepository;

    @Autowired
    public QuoteSectionServiceImpl(LoggedUser loggedUser, QuoteSectionRepository quoteSectionRepository) {
        this.loggedUser = loggedUser;
        this.quoteSectionRepository = quoteSectionRepository;
    }
}
