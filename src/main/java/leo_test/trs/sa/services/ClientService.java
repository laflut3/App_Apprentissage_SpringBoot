package leo_test.trs.sa.services;

import jakarta.persistence.EntityNotFoundException;
import leo_test.trs.sa.dto.ClientDTO;
import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.mapper.ClientDTOMapper;
import leo_test.trs.sa.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientDTOMapper mapper;

    @Autowired
    private final ClientRepository clientRepository;

    public void creer(Client client) {
        Client client1 = this.clientRepository.findByEmail(client.getEmail());
        if(client1 == null) {
            this.clientRepository.save(client);
        }
    }

    public Stream<ClientDTO> rechercherAll() {
        return this.clientRepository.findAll().stream().map(mapper);
    }

    public Client lire(int id) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        return optionalClient.orElseThrow(
                () -> new EntityNotFoundException("Aucun client n'existe avec cet id")
        );
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
