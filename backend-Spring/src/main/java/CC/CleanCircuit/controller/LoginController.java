package CC.CleanCircuit.controller;

import CC.CleanCircuit.dtos.LoginDTO;
import CC.CleanCircuit.dtos.NewPasswordDTO;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.services.LoginService;
import CC.CleanCircuit.services.SenhaService;
import CC.CleanCircuit.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> verificarLogin(@Valid @RequestBody LoginDTO dto) {
        return loginService.efetuarLogin(dto);
    }

    @PostMapping("/new-password")
    public ResponseEntity<ApiResponseDTO> novaSenha(@Valid @RequestBody NewPasswordDTO dto) {
        return loginService.criarNovaSenha(dto.getToken(), dto.getNewPassword());
    }


    @PostMapping("/reset-password/{email}")
    public ResponseEntity<ApiResponseDTO> resetSenha(@Email @PathVariable String email) {
        return loginService.resetarSenha(email);
    }
}
