package leo_test.trs.sa.services;

import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.entites.Sentiment;
import leo_test.trs.sa.enums.TypeSentiment;
import leo_test.trs.sa.repository.SentimentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SentimentServiceTest {

    @Mock
    private ClientService clientService;

    @Mock
    private SentimentRepository sentimentRepository;

    @InjectMocks
    private SentimentService sentimentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreer_NouveauSentiment() {
        Client client = new Client();
        client.setEmail("test@gmail.com");

        Sentiment sentiment = new Sentiment();
        sentiment.setTexte("Ce site est vraiment super");
        sentiment.setType(TypeSentiment.POSITIF);
        sentiment.setClient(client);

        when(clientService.lireOuCreer(any(Client.class))).thenReturn(client);

        sentimentService.creer(sentiment);

        verify(clientService, times(1)).lireOuCreer(client);

        verify(sentimentRepository, times(1)).save(sentiment);
    }

    @Test
    public void testRechercherAll() {
        List<Sentiment> sentiments = new ArrayList<>();

        Sentiment sentiment1 = new Sentiment();
        sentiment1.setTexte("Sentiment 1");

        Sentiment sentiment2 = new Sentiment();
        sentiment2.setTexte("Sentiment 2");

        sentiments.add(sentiment1);
        sentiments.add(sentiment2);

        when(sentimentRepository.findAll()).thenReturn(sentiments);

        List<Sentiment> result = sentimentService.rechercherAll();

        assertEquals(2, result.size());
        assertEquals("Sentiment 1", result.get(0).getTexte());
        assertEquals("Sentiment 2", result.get(1).getTexte());
        verify(sentimentRepository, times(1)).findAll();
    }

    @Test
    public void testRechercherSentiment_Existe() {
        Sentiment sentiment = new Sentiment();
        sentiment.setId(1);
        sentiment.setTexte("Un sentiment positif");

        when(sentimentRepository.findById(1)).thenReturn(Optional.of(sentiment));

        Sentiment result = sentimentService.rechercherSentiment(1);

        assertNotNull(result);
        assertEquals("Un sentiment positif", result.getTexte());
        verify(sentimentRepository, times(1)).findById(1);
    }

    @Test
    public void testRechercherSentiment_NExistePas() {
        when(sentimentRepository.findById(1)).thenReturn(Optional.empty());

        Sentiment result = sentimentService.rechercherSentiment(1);

        assertNull(result);
        verify(sentimentRepository, times(1)).findById(1);
    }

    @Test
    public void testSupprimer() {
        sentimentService.supprimer(1);
        verify(sentimentRepository, times(1)).deleteById(1);
    }
}
