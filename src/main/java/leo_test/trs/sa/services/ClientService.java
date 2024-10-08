package leo_test.trs.sa.services;

import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void creer(Client client) {
        Client client1 = this.clientRepository.findByEmail(client.getEmail());
        if(client1 == null) {
            this.clientRepository.save(client);
        }
    }

    public List<Client> rechercherAll() {
        return this.clientRepository.findAll();
    }

    public Client lire(int id) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public Client lireOuCreer(Client client) {
        Client client1 = this.clientRepository.findByEmail(client.getEmail());
        if(client1 == null) {
            client1 = this.clientRepository.save(client);
        }
        return client1;
    }

    public void modifier(int id, Client client) {
        Client clientBDD = this.lire(id);
        if(clientBDD.getId() == client.getId()) {
            clientBDD.setTelephone(client.getTelephone());
            clientBDD.setEmail(client.getEmail());
            this.clientRepository.save(clientBDD);
        }

    }
}
