package leo_test.trs.sa.controller;

import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Client> getClients() {
        return this.clientService.rechercherAll();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Client lire(@PathVariable int id) {
        return this.clientService.lire(id);
    }


}
