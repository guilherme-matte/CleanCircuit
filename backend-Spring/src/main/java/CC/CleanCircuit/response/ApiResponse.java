package CC.CleanCircuit.response;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiResponse {
    public ResponseEntity<ApiResponseDTO> resposta(Object obj, String message, int code) {
        ApiResponseDTO response = new ApiResponseDTO(obj, message, code);
        return ResponseEntity.status(code).body(response);
    }

}
