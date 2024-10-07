package leo_test.trs.sa.services;

import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.entites.Sentiment;
import leo_test.trs.sa.repository.SentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SentimentRepository sentimentRepository;

    public void creer(Sentiment sentiment) {
        Client client = this.clientService.lireOuCreer(sentiment.getClient());
        sentiment.setClient(client);
        this.sentimentRepository.save(sentiment);
    }

    public List<Sentiment> rechercherAll() {
        return this.sentimentRepository.findAll();
    }

    public Sentiment rechercherSentiment(int id) {
        return this.sentimentRepository.findById(id).orElse(null);
    }

    public void supprimer(int id) {
        this.sentimentRepository.deleteById(id);
    }
}
