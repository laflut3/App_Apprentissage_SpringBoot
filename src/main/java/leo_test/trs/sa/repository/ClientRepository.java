package leo_test.trs.sa.repository;


import leo_test.trs.sa.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //totologie car extend JPA definie deja l'interface comme repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);
}
