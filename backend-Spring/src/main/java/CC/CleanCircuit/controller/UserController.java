package CC.CleanCircuit.controller;

import CC.CleanCircuit.dtos.UserDTO;
import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SenhaService senhaService;
    @Autowired
    private InvestidorRepository investidorRepository;
    private final MailService mailService;

    public UserController(MailService mailService) {
        this.mailService = mailService;
    }


    @PostMapping(value = "/user/{email}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseDTO> uploadImagemPerfil(@PathVariable String email, @RequestPart("file") MultipartFile file) {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            return ApiResponse.resposta(null, "Usuário não encontrado", 404);
        }

        if (file == null || file.isEmpty()) {
            return ApiResponse.resposta(null, "Arquivo não enviado", 400);
        }

        try {


            String uploadDir = "uploads";

            Files.createDirectories(Paths.get(uploadDir));

            String originalFilename = file.getOriginalFilename(); // ex: "foto.jpg"
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String filename = user.getCpf() + ".png";
            Path path = Paths.get(uploadDir).resolve(filename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            user.setUrlProfileImage("uploads/" + filename);

            userRepository.save(user);
            return ApiResponse.resposta(user, "Imagem alterada com sucesso", 200);
        } catch (IOException e) {
            return ApiResponse.resposta(null, "Erro ao salvar imagem: " + e.getMessage(), 500);
        }
    }


    @DeleteMapping("/user/{email}/upload")
    public ResponseEntity<ApiResponseDTO> deletarImagem(@PathVariable String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return ApiResponse.resposta(null, "Usuario não encontrado", 404);
        }
        userService.deletarImagemFunc(user);
        return ApiResponse.resposta(user, "Imagem deletada com sucesso", 200);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<ApiResponseDTO> retornarUsuario(@PathVariable String email) {
        UserDTO userDTO = new UserDTO();
        if (email.isEmpty()) {
            return ApiResponse.resposta(null, "Email vazio", 404);
        }
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return ApiResponse.resposta(null, "Usuário não encontrado", 404);
        }
        userDTO.setUserEntity(user);
        if (user.getUrlProfileImage() != null) {
            File file = new File(user.getUrlProfileImage());
            userDTO.setHasProfileImage(file.exists());
        }


        return ApiResponse.resposta(userDTO, "Usuário encontrado com sucesso", 200);
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<ApiResponseDTO> atualizarUsuario(@RequestBody UserEntity user, @PathVariable String email) {
        UserEntity userAtual = userRepository.findByEmail(email);
        userAtual.setNomeCompleto(user.getNomeCompleto());
        userAtual.setDate(user.getDate());
        userAtual.setTelefone(user.getTelefone());
        userAtual.setEmail(user.getEmail());

        userRepository.save(userAtual);
        return ApiResponse.resposta(userAtual, "Usuário alterado com sucesso", 200);
    }


    @PostMapping("/user")
    public ResponseEntity<ApiResponseDTO> criarUsuario(@RequestBody UserEntity user) {
        if (user == null) {
            return ApiResponse.resposta(null, "Body vazio", 404);
        }

        String msg = userService.verificarEmailCPF(user);
        if (msg != null) {
            return ApiResponse.resposta(null, msg, 409);
        }

        InvestidorEntity investidor = new InvestidorEntity();

        investidor.setCpf(user.getCpf());
        investidor.setNomeCompleto(user.getNomeCompleto());

        investidorRepository.save(investidor);
        user.setSenha(senhaService.hashSenha(user.getSenha()));
        userRepository.save(user);
        mailService.enviarEmailBoasVindas(user.getEmail(), user.getNomeCompleto());
        return ApiResponse.resposta(user, "Cadastro realizado com sucesso!\n Faça login para acessar o sistema", 200);

    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<ApiResponseDTO> deleteUser(@PathVariable String email) {
        return userService.deletarUser(email);
    }
}
