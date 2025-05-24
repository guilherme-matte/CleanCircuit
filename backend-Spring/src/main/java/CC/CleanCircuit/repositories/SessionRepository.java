package CC.CleanCircuit.repositories;

import CC.CleanCircuit.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    SessionEntity findByUsuario(String user);

    void deleteByUsuario(String user);
}
