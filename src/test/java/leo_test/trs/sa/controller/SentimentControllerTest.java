package leo_test.trs.sa.controller;

import leo_test.trs.sa.entites.Sentiment;
import leo_test.trs.sa.enums.TypeSentiment;
import leo_test.trs.sa.services.SentimentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SentimentController.class)
public class SentimentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SentimentService sentimentService;

    @BeforeEach
    public void setUp() {
        // Configuration initiale avant chaque test, si nécessaire
    }

    @Test
    public void testCreerSentiment() throws Exception {
        Sentiment sentiment = new Sentiment();
        sentiment.setTexte("Ce site est incroyable");
        sentiment.setType(TypeSentiment.POSITIF);

        String sentimentJson = "{\"texte\": \"Ce site est incroyable\", \"type\": \"POSITIF\"}";

        mockMvc.perform(post("/sentiment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sentimentJson))
                .andExpect(status().isCreated());

        verify(sentimentService, times(1)).creer(any(Sentiment.class));
    }

    @Test
    public void testGetAllSentiments() throws Exception {
        Sentiment sentiment1 = new Sentiment();
        sentiment1.setId(1);
        sentiment1.setTexte("Sentiment 1");
        sentiment1.setType(TypeSentiment.POSITIF);

        Sentiment sentiment2 = new Sentiment();
        sentiment2.setId(2);
        sentiment2.setTexte("Sentiment 2");
        sentiment2.setType(TypeSentiment.NEGATIF);

        when(sentimentService.rechercherAll(null)).thenReturn(Arrays.asList(sentiment1, sentiment2));

        mockMvc.perform(get("/sentiment")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].texte").value("Sentiment 1"))
                .andExpect(jsonPath("$[1].texte").value("Sentiment 2"));
    }

    @Test
    public void testGetSentiment_Existe() throws Exception {
        Sentiment sentiment = new Sentiment();
        sentiment.setId(1);
        sentiment.setTexte("Sentiment spécifique");
        sentiment.setType(TypeSentiment.POSITIF);

        when(sentimentService.rechercherSentiment(1)).thenReturn(sentiment);

        mockMvc.perform(get("/sentiment/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.texte").value("Sentiment spécifique"))
                .andExpect(jsonPath("$.type").value("POSITIF"));
    }

    @Test
    public void testGetSentiment_NExistePas() throws Exception {
        when(sentimentService.rechercherSentiment(1)).thenReturn(null);

        mockMvc.perform(get("/sentiment/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // Vérifie que la réponse est vide
    }

    @Test
    public void testSupprimerSentiment() throws Exception {
        mockMvc.perform(delete("/sentiment/1"))
                .andExpect(status().isAccepted());

        verify(sentimentService, times(1)).supprimer(1);
    }
}
