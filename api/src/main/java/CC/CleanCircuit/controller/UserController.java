package CC.CleanCircuit.controller;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private ApiResponse response;

    @PostMapping("/user")
    public ResponseEntity<ApiResponseDTO> criarUsuario(UserEntity user) {
        return response.resposta(null, "teste realizado com sucesso!", 200);
    }
}
