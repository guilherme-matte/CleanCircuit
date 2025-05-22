package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SenhaService senhaService;
    @Autowired
    private MailService mailService;


    public boolean verificarEmail(String email) {
        UserEntity emailEncontrado = userRepository.findByEmail(email);

        if (emailEncontrado == null || emailEncontrado.equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public void resetarSenha(UserEntity usuario) {
        String senhaTemporaria = SenhaService.gerarSenha();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(senhaTemporaria);
        usuario.setSenhaTemporaria(passwordEncoder.encode(senhaTemporaria));
        usuario.setSenhaTemporariaBoolean(true);
        mailService.enviarEmailResetSenha(usuario.getEmail(), senhaTemporaria);
        userRepository.save(usuario);
    }

    public void deletarImagemFunc(UserEntity user) {
        try {
            Files.deleteIfExists(Path.of(user.getUrlProfileImage()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        user.setUrlProfileImage(null);
        userRepository.save(user);
    }
}
