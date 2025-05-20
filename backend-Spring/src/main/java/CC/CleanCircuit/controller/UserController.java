package CC.CleanCircuit.controller;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.services.MailService;
import CC.CleanCircuit.services.SenhaService;
import CC.CleanCircuit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UserController {
    @Autowired
    private ApiResponse response;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SenhaService senhaService;
    @Autowired
    private MailService mailService;


    @PutMapping(value = "/user/{email}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseDTO> uploadImagemPerfil(@PathVariable String email, @RequestPart("file") MultipartFile file) {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            return response.resposta(null, "Usuário não encontrado", 404);
        }

        if (file == null || file.isEmpty()) {
            return response.resposta(null, "Arquivo não enviado", 400);
        }

        try {


            String uploadDir = "uploads";

            Files.createDirectories(Paths.get(uploadDir));

            String originalFilename = file.getOriginalFilename(); // ex: "foto.jpg"
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String filename = user.getCpf() + extension;
            Path path = Paths.get(uploadDir).resolve(filename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            user.setUrlProfileImage("/uploads/" + filename);
            userRepository.save(user);
            return response.resposta(user, "Imagem alterada com sucesso", 200);
        } catch (IOException e) {
            return response.resposta(null, "Erro ao salvar imagem: " + e.getMessage(), 500);
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<ApiResponseDTO> retornarUsuario(@PathVariable String email) {
        if (email.isEmpty()) {
            return response.resposta(null, "Email vazio", 404);
        }
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return response.resposta(null, "Usuário não encontrado", 404);
        }
        return response.resposta(user, "Usuário encontrado com sucesso", 200);
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<ApiResponseDTO> atualizarUsuario(@RequestBody UserEntity user, @PathVariable String email) {
        UserEntity userAtual = userRepository.findByEmail(email);
        userAtual.setNomeCompleto(user.getNomeCompleto());
        userAtual.setDate(user.getDate());
        userAtual.setTelefone(user.getTelefone());
        userAtual.setEmail(user.getEmail());

        userRepository.save(userAtual);
        return response.resposta(userAtual, "Usuário alterado com sucesso", 200);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponseDTO> deletarUsuario(@PathVariable Long id) {
        userRepository.deleteById(id);
        return response.resposta(null, "Usuário deletado com sucesso", 200);

    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponseDTO> criarUsuario(@RequestBody UserEntity user) {
        if (user == null) {
            return response.resposta(null, "Body vazio", 404);
        }
        user.setSenha(senhaService.hashSenha(user.getSenha()));
        userRepository.save(user);
        mailService.enviarEmailBoasVindas(user.getEmail(), user.getNomeCompleto());
        return response.resposta(user, "Cadastro realizado com sucesso", 200);

    }

}
