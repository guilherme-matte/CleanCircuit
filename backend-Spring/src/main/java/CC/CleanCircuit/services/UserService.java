package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SenhaService senhaService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ApiResponse response;


    public boolean verificarEmail(String email) {
        UserEntity emailEncontrado = userRepository.findByEmail(email);

        if (emailEncontrado == null || emailEncontrado.equals(email)) {
            return true;
        } else {
            return false;
        }
    }


    public void deletarImagemFunc(UserEntity user) {
        try {
            Files.deleteIfExists(Path.of(user.getUrlProfileImage()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        user.setUrlProfileImage(null);
        userRepository.save(user);
    }

    public ResponseEntity<ApiResponseDTO> deletarUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return response.resposta(null, "Usuário não encontrado", 404);
        }

        try {
            userRepository.delete(user);
            if (user.getUrlProfileImage() != null) {
                Files.deleteIfExists(Path.of(user.getUrlProfileImage()));
            }
            return response.resposta(null, "Usuário deletado com sucesso, saindo do sistema.", 200);
        } catch (IOException e) {
            return response.resposta(null, "Erro ao deletar usuário. " + e.getMessage(), 500);
        }
    }
}
