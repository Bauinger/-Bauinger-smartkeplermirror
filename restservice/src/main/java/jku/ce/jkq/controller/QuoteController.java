package jku.ce.jkq.controller;

import jku.ce.jkq.entities.Quote;
import jku.ce.jkq.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("quote")
    public List<Quote> getQuote() {
        return quoteService.getQuote();
    }

}
