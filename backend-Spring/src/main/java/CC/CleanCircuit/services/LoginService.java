package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<ApiResponseDTO> criarNovaSenha(String email, String novaSenha) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return response.resposta(null, "Usuário não encontrado, contato os administradores", 404);
        }
        user.setSenha(senhaService.hashSenha(novaSenha));
        userRepository.save(user);
        return response.resposta(user, "Senha alterada com sucesso!", 200);
    }

}
