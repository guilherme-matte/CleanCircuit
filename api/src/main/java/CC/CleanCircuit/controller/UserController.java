package CC.CleanCircuit.controller;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private ApiResponse response;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<ApiResponseDTO> retornarUsuario(UserEntity user) {
        return response.resposta(null, "teste realizado com sucesso!", 200);
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponseDTO> criarUsuario(UserEntity user) {
        if (user == null) {
            return response.resposta(null, "Body vazio", 404);
        }
        System.out.println(user.getCpf());
        userRepository.save(user);
        return response.resposta(user, "Cadastro realizado com sucesso", 200);
    }
}
