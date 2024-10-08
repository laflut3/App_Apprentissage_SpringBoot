package leo_test.trs.sa.controller;

import leo_test.trs.sa.entites.Sentiment;
import leo_test.trs.sa.enums.TypeSentiment;
import leo_test.trs.sa.services.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "sentiment", produces = APPLICATION_JSON_VALUE)
public class SentimentController {

    @Autowired
    private SentimentService sentimentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Sentiment sentiment){
        this.sentimentService.creer(sentiment);
    }

    @GetMapping()
    public List<Sentiment> getAllSentiments(@RequestParam(required = false) TypeSentiment type){
        return this.sentimentService.rechercherAll(type);
    }

    @GetMapping(path = "{id}")
    public Sentiment getSentiment(@PathVariable int id) {
        return this.sentimentService.rechercherSentiment(id);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable int id){
        this.sentimentService.supprimer(id);
    }

}
