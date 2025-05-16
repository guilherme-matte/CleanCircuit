package CC.CleanCircuit.controller;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.services.MailService;
import CC.CleanCircuit.services.SenhaService;
import CC.CleanCircuit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private ApiResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SenhaService senhaService;
    @Autowired
    private MailService mailService;

    @GetMapping("/user/{cpf}")
    public ResponseEntity<ApiResponseDTO> retornarUsuario(@PathVariable String cpf) {
        if (cpf.isEmpty()) {
            return response.resposta(null, "CPF vazio", 404);
        }
        UserEntity user = userRepository.findByCpf(cpf);
        if (user == null) {
            return response.resposta(null, "Usuario não encontrado", 404);
        }
        return response.resposta(user, "Usuário encontrado com sucesso", 200);
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponseDTO> atualizarUsuario(@RequestBody UserEntity user) {
        UserEntity userAtual = userRepository.findByCpf(user.getCpf());

        userAtual.setNomeCompleto(user.getNomeCompleto());

        if (!userService.verificarEmail(user.getEmail())) {
            return response.resposta(user.getEmail(), "Email já cadastrado", 404);

        } else {
            userAtual.setEmail(user.getEmail());
        }
        userRepository.save(userAtual);
        return response.resposta(userAtual, "Usuário alterado com sucesso", 200);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponseDTO> deletarUsuario(@PathVariable Long id) {
        userRepository.deleteById(id);
        return response.resposta(null, "Usuário deletado com sucesso", 200);

    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponseDTO> criarUsuario(@RequestBody UserEntity user) {
        if (user == null) {
            return response.resposta(null, "Body vazio", 404);
        }
        user.setSenha(senhaService.hashSenha(user.getSenha()));
        userRepository.save(user);
        mailService.enviarEmailBoasVindas(user.getEmail(), user.getNomeCompleto());
        return response.resposta(user, "Cadastro realizado com sucesso", 200);

    }

}
