package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.entity.sections.QuoteSection;
import bg.softuni.movieapp.repository.sections.QuoteSectionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuoteSectionServiceImplTest {

    @Mock
    private QuoteSectionRepository quoteSectionRepository;

    private QuoteSectionServiceImpl quoteSectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteSectionService = new QuoteSectionServiceImpl(quoteSectionRepository);
    }

    @Test
    void testCreateQuoteSection() {
        QuoteSection quoteSection = new QuoteSection();

        quoteSectionService.createQuoteSection(quoteSection);

        ArgumentCaptor<QuoteSection> argumentCaptor = ArgumentCaptor.forClass(QuoteSection.class);
        verify(quoteSectionRepository, times(1)).save(argumentCaptor.capture());

        QuoteSection capturedQuoteSection = argumentCaptor.getValue();
    }
}