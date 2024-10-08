package leo_test.trs.sa.services;

import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreer_NouveauClient() {
        Client client = new Client();
        client.setEmail("test@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(null);

        clientService.creer(client);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testCreer_ClientExisteDeja() {
        Client client = new Client();
        client.setEmail("test@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(client);

        clientService.creer(client);

        verify(clientRepository, never()).save(any(Client.class));
    }

//    @Test
//    public void testRechercherAll() {
//        List<Client> clients = new ArrayList<>();
//        clients.add(new Client());
//        clients.add(new Client());
//
//        when(clientRepository.findAll()).thenReturn(clients);
//
//        List<Client> result = clientService.rechercherAll();
//
//        assertEquals(2, result.size());
//        verify(clientRepository, times(1)).findAll();
//    }

    @Test
    public void testLire_ClientExiste() {
        Client client = new Client();
        client.setId(1);

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        Client result = clientService.lire(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    public void testLire_ClientNExistePas() {
        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        Client result = clientService.lire(1);

        assertNull(result);
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    public void testLireOuCreer_ClientExiste() {
        Client client = new Client();
        client.setEmail("test@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(client);

        Client result = clientService.lireOuCreer(client);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    public void testLireOuCreer_NouveauClient() {
        Client client = new Client();
        client.setEmail("test@example.com");

        when(clientRepository.findByEmail(client.getEmail())).thenReturn(null);
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.lireOuCreer(client);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(clientRepository, times(1)).save(client);
    }
}
