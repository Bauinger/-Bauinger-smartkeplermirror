package jku.ce.jkq.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quotes")
public class Quote {

    @Id
    public String id;

    public String quoteText;

    public Quote() {}

    public Quote(String quote) {
        this.quoteText = quote;
    }
}
