package jku.ce.jkq.repository;

import java.util.List;
import jku.ce.jkq.entities.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, String> {
    @Override
    List<Quote> findAll();
}