package CC.CleanCircuit.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class SenhaService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hashSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean verificarSenha(String senhaDigitada, String senhaHash) {
        return passwordEncoder.matches(senhaDigitada, senhaHash);
    }

}
