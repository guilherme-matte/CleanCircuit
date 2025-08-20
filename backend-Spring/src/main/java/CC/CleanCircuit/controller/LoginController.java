package CC.CleanCircuit.controller;

import CC.CleanCircuit.Component.JwtUtil;
import CC.CleanCircuit.dtos.LoginDTO;
import CC.CleanCircuit.dtos.NewPasswordDTO;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.services.LoginService;
import CC.CleanCircuit.services.SenhaService;
import CC.CleanCircuit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RequestMapping(value = "/auth")
@RestController
public class LoginController {


    @Autowired
    private SenhaService senhaServices;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    private final JwtUtil jwtUtil;

    public LoginController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("API ONLINE");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> verificarLogin(@RequestBody LoginDTO dto) {
        if (dto == null) {
            return ApiResponse.resposta(null, "Campos vazios", 404);
        }
        UserEntity user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            return ApiResponse.resposta(null, "Usuário ou senha incorreto(s)", 404);
        }

        if (senhaServices.verificarSenha(dto.getPassword(), user.getSenha())) {
            String token = jwtUtil.generateToken(user.getEmail());

            LinkedHashMap<String, Object> response = new LinkedHashMap<>();

            response.put("User", user);
            response.put("token", token);

            return ApiResponse.resposta(response, "Login realizado com sucesso.", 200);


        }
        return ApiResponse.resposta(null, "Usuário ou senha incorreto(s)", 404);
    }

    @PostMapping("/new-password")
    public ResponseEntity<ApiResponseDTO> novaSenha(@RequestBody NewPasswordDTO dto) {
        return loginService.criarNovaSenha(dto.getToken(), dto.getNewPassword());
    }


    @PostMapping("/reset-password/{email}")
    public ResponseEntity<ApiResponseDTO> resetSenha(@PathVariable String email) {

        if (email == null) {
            return ApiResponse.resposta(null, "campo email vazio", 404);
        }
        UserEntity usuario = userRepository.findByEmail(email);
        if (usuario == null) {

            return ApiResponse.resposta(null, "Email não encontrado", 404);
        }
        loginService.gerarTokenResetSenha(usuario);
        return ApiResponse.resposta(null, "Email enviado com sucesso!", 200);
    }
}
