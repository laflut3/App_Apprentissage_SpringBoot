package leo_test.trs.sa.controller;

import leo_test.trs.sa.entites.Client;
import leo_test.trs.sa.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        // Configuration initiale avant chaque test, si nécessaire
    }

    @Test
    public void testCreerClient() throws Exception {
        Client client = new Client();
        client.setEmail("test@example.com");

        String clientJson = "{\"email\": \"test@example.com\", \"nom\": \"Nom Test\"}";

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated());

        Mockito.verify(clientService, Mockito.times(1)).creer(any(Client.class));
    }

    @Test
    public void testGetClients() throws Exception {
        Client client1 = new Client();
        client1.setId(1);
        client1.setEmail("client1@example.com");

        Client client2 = new Client();
        client2.setId(2);
        client2.setEmail("client2@example.com");

        mockMvc.perform(get("/client")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("client1@example.com"))
                .andExpect(jsonPath("$[1].email").value("client2@example.com"));
    }

    @Test
    public void testLireClient_Existe() throws Exception {
        Client client = new Client();
        client.setId(1);
        client.setEmail("client@example.com");

        when(clientService.lire(1)).thenReturn(client);

        mockMvc.perform(get("/client/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("client@example.com"));
    }

    @Test
    public void testLireClient_NExistePas() throws Exception {
        when(clientService.lire(1)).thenReturn(null);

        mockMvc.perform(get("/client/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // Vérifie que la réponse est vide
    }
}
