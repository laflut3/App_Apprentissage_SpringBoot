package leo_test.trs.sa.repository;

import leo_test.trs.sa.entites.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentimentRepository  extends JpaRepository<Sentiment, Integer> {
}
