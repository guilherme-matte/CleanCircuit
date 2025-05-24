package CC.CleanCircuit.controller;

import CC.CleanCircuit.dtos.LoginDTO;
import CC.CleanCircuit.dtos.NewPasswordDTO;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.services.LoginService;
import CC.CleanCircuit.services.SenhaService;
import CC.CleanCircuit.services.SessionService;
import CC.CleanCircuit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @Autowired
    private SenhaService senhaServices;
    @Autowired
    private ApiResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SessionService sessionService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> verificarLogin(@RequestBody LoginDTO dto) {
        if (dto == null) {
            return response.resposta(null, "Campos vazios", 404);
        }
        UserEntity user = userRepository.findByEmail(dto.getEmail());


        if (senhaServices.verificarSenha(dto.getPassword(), user.getSenha())) {


            if (sessionService.verificarUsuarioLogado(user.getEmail())) {
                sessionService.salvarSessao(user.getEmail());
                return response.resposta(null, "Login realizado com sucesso.", 200);
            }
            return response.resposta(null, "Usuário logado, contato um administrador.", 404);


        }
        return response.resposta(null, "Usuário ou senha incorreto(s)", 404);
    }

    @PostMapping("/new-password")
    public ResponseEntity<ApiResponseDTO> novaSenha(@RequestBody NewPasswordDTO dto) {
        return loginService.criarNovaSenha(dto.getToken(), dto.getNewPassword());
    }

    @DeleteMapping("/shutdown-session/{email}")
    public void shutdownSession(@PathVariable String email) {
        sessionService.deletarSessao(email);
    }

    @PostMapping("/reset-password/{email}")
    public ResponseEntity<ApiResponseDTO> resetSenha(@PathVariable String email) {

        if (email == null) {
            return response.resposta(null, "campo email vazio", 404);
        }
        UserEntity usuario = userRepository.findByEmail(email);
        if (usuario == null) {

            return response.resposta(null, "Email não encontrado", 404);
        }
        loginService.gerarTokenResetSenha(usuario);
        return response.resposta(null, "Email enviado com sucesso!", 200);
    }
}
