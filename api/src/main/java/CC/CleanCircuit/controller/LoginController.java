package CC.CleanCircuit.controller;

import CC.CleanCircuit.dtos.LoginDTO;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.services.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {


    @Autowired
    private SenhaService senhaServices;
    @Autowired
    private ApiResponse response;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<ApiResponseDTO> verificarLogin(@RequestBody LoginDTO dto) {
        if (dto == null) {
            return response.resposta(null, "Campos vazios", 404);
        }
        UserEntity user = userRepository.findByCpf(dto.getUsername());


        if (user == null || !senhaServices.verificarSenha(dto.getPassword(), user.getSenha()) || user.isSenhaTemporariaBoolean() && senhaServices.verificarSenha(dto.getPassword(), user.getSenhaTemporaria())) {
            return response.resposta(null, "Usuario ou senha incorreto(s)", 404);
        }
        return response.resposta(null, "Login realizado com sucesso", 200);
    }

    @GetMapping("/reset-password/{email}")
    public ResponseEntity<ApiResponseDTO> resetSenha(@PathVariable String email) {

        if (email == null) {
            return response.resposta(null, "campo email vazio", 404);
        }
        UserEntity usuario = userRepository.findByEmail(email);

    }
}
