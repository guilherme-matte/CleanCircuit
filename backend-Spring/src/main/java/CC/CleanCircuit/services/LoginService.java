package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private SenhaService senhaService;
    @Autowired
    private ApiResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    public ResponseEntity<ApiResponseDTO> criarNovaSenha(String token, String novaSenha) {
        UserEntity user = userRepository.findByResetToken(token);
        if (user == null || user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            return response.resposta(null, "Token inválido ou expirado", 404);
        }
        user.setSenha(senhaService.hashSenha(novaSenha));
        user.setResetTokenExpiration(null);
        user.setResetToken(null);
        userRepository.save(user);
        return response.resposta(user, "Senha alterada com sucesso! Faça login novamente.", 200);
    }

    public void gerarTokenResetSenha(UserEntity user) {
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiration(LocalDateTime.now().plusHours(1));
        userRepository.save(user);

        String link = "http://localhost:8000/criar-nova-senha?token=" + token;
        mailService.enviarEmailResetSenha(user.getEmail(), link);
    }
}

