package CC.CleanCircuit.services;

import CC.CleanCircuit.dtos.LoginDTO;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class LoginService {
    @Autowired
    private SenhaService senhaService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    private final MailService mailService;


    public LoginService(MailService mailService) {
        this.mailService = mailService;
    }

    public ResponseEntity<ApiResponseDTO> criarNovaSenha(String token, String novaSenha) {
        UserEntity user = userRepository.findByResetToken(token);
        if (user == null || user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            return ApiResponse.resposta(null, "Token inválido ou expirado", 404);
        }
        user.setSenha(senhaService.hashSenha(novaSenha));
        user.setResetTokenExpiration(null);
        user.setResetToken(null);
        userRepository.save(user);
        return ApiResponse.resposta(user, "Senha alterada com sucesso! Faça login novamente.", 200);
    }

    public String gerarTokenResetSenha(UserEntity user) {
        Random random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        user.setResetToken(token);
        user.setResetTokenExpiration(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        return token;

    }

    public ResponseEntity<ApiResponseDTO> resetarSenha(String email) {
        UserEntity usuario = userRepository.findByEmail(email);
        if (usuario == null) {
            return ApiResponse.resposta(null, "Email não encontrado", 404);
        }
        String token = gerarTokenResetSenha(usuario);
        String link = "http://localhost:8000/criar-nova-senha?token=" + token;
        mailService.enviarEmailResetSenha(email, link);
        return ApiResponse.resposta(null, "Email enviado com sucesso!", 200);
    }

    public ResponseEntity<ApiResponseDTO> efetuarLogin(LoginDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            return ApiResponse.resposta(null, "Usuário ou senha incorreto(s)", 404);
        }

        if (senhaService.verificarSenha(dto.getPassword(), user.getSenha())) {


            return ApiResponse.resposta(user, "Login realizado com sucesso.", 200);


        }
        return ApiResponse.resposta(null, "Usuário ou senha incorreto(s)", 404);
    }
}

