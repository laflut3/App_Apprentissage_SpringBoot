package leo_test.trs.sa.mapper;

import leo_test.trs.sa.dto.ClientDTO;
import leo_test.trs.sa.entites.Client;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientDTOMapper implements Function<Client, ClientDTO> {

    @Override
    public ClientDTO apply(Client client) {
        return new ClientDTO(client.getId(), client.getEmail(), client.getTelephone());
    }
}
