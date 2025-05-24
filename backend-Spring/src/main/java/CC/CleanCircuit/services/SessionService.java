package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.SessionEntity;
import CC.CleanCircuit.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {
    @Autowired
    private SessionRepository repository;


    public boolean verificarUsuarioLogado(String user) {
        SessionEntity session = repository.findByUsuario(user);

        return session == null;
    }

    public void salvarSessao(String email) {
        SessionEntity user = new SessionEntity();
        user.setUsuario(email);
        user.setTime(LocalDateTime.now());
        repository.save(user);
    }

    public void deletarSessao(String email) {
        SessionEntity user = repository.findByUsuario(email);
        repository.deleteById(user.getId());
    }
}
