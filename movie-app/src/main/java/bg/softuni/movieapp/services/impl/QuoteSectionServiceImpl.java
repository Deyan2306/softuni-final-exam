package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.repository.sections.QuoteSectionRepository;
import bg.softuni.movieapp.services.QuoteSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteSectionServiceImpl implements QuoteSectionService {

    private final QuoteSectionRepository quoteSectionRepository;

    @Autowired
    public QuoteSectionServiceImpl(QuoteSectionRepository quoteSectionRepository) {
        this.quoteSectionRepository = quoteSectionRepository;
    }

    @Override
    public void createQuoteSection(QuoteSection quoteSection) {
        this.quoteSectionRepository.save(quoteSection);
    }
}
