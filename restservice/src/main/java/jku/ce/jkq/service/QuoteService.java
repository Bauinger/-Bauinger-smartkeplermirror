package jku.ce.jkq.service;

import jku.ce.jkq.entities.Quote;
import jku.ce.jkq.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private final QuoteRepository repository;


    @Autowired
    public QuoteService(QuoteRepository repository){
        this.repository = repository;
    }

    public List<Quote> getQuote() {
       return repository.findAll();
    }
}
