package CC.CleanCircuit.response;

import org.springframework.http.ResponseEntity;

public class ApiResponse {
    public static ResponseEntity<ApiResponseDTO> resposta(Object obj, String message, int code) {
        ApiResponseDTO response = new ApiResponseDTO(obj, message, code);
        return ResponseEntity.status(code).body(response);
    }

}
