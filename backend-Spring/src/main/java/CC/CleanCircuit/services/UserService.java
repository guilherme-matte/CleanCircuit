package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean verificarEmail(String email) {
        UserEntity emailEncontrado = userRepository.findByEmail(email);

        if (emailEncontrado == null || emailEncontrado.equals(email)) {
            return true;
        } else {
            return false;
        }
    }
}
