package CC.CleanCircuit.controller;

import CC.CleanCircuit.Component.JwtUtil;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.services.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public AuthController(JwtUtil jwtUtil, RefreshTokenService refreshTokenService, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String requestToken = request.get("refreshToken");
        return refreshTokenService.findByToken(requestToken)
                .map(refreshToken -> {
                    if (!refreshTokenService.isValid(refreshToken)) {
                        return ResponseEntity.status(403).body("Refresh token expirado");
                    }
                    UserEntity user = refreshToken.getUser();
                    String token = jwtUtil.generateToken(user.getEmail());
                    return ResponseEntity.ok(Map.of(
                            "accessToken", token,
                            "refreshToken", refreshToken.getToken()
                    ));
                }).orElse(ResponseEntity.status(403).body("Refresh token inválido"));
    }
}
