package leo_test.trs.sa.repository;

import leo_test.trs.sa.entites.Sentiment;
import leo_test.trs.sa.enums.TypeSentiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentimentRepository  extends JpaRepository<Sentiment, Integer> {
    List<Sentiment> findByType(TypeSentiment type);
}
