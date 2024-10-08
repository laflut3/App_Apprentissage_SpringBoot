package leo_test.trs.sa.controller;

import leo_test.trs.sa.dto.ClientDTO;
import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Client client) {
        this.clientService.creer(client);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Stream<ClientDTO> getClients() {
        return this.clientService.rechercherAll();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Client lire(@PathVariable int id) {
        return this.clientService.lire(id);
    }

    /* Methode 2:
      //@ResponseStatus(BAD_REQUEST)
      //@ExceptionHandler({EntityNotFoundException.class})
      public ErrorEntity handleException(EntityNotFoundException e) {
         return new ErrorEntity(null, e.getMessage());
      }
      */

    /* Methode 1 :
     * //@GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
     * public ResponseEntity lire(@PathVariable int id) {
     *     try {
     *         Client client = this.clientService.lire(id);
     *         return ResponseEntity.ok(client);
     *     }catch (EntityNotFoundException e) {
     *         return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
     *     }
     * }
     * */

    @ResponseStatus(NO_CONTENT)
    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Client client) {
        this.clientService.modifier(id, client);
    }
}
