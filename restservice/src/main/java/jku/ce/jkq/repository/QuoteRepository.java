package jku.ce.jkq.repository;

import java.util.Optional;
import jku.ce.jkq.entities.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuoteRepository extends MongoRepository<String, Quote> {
    @Query("{$sample: {size: 1} }")
    Optional<Quote> findRandom();
}
