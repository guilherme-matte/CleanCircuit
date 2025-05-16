package CC.CleanCircuit.services;

import CC.CleanCircuit.entities.UserEntity;
import CC.CleanCircuit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);//para quem será enviado o email
        message.setSubject(subject);//Assunto
        message.setText(text);//corpo do email
        message.setFrom("redefinicao.ic@gmail.com");

        mailSender.send(message);
    }

    public void enviarEmailResetSenha(String email, UserEntity usuario) {
        String senhaGerada = SenhaService.gerarSenha();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setSenhaTemporaria(passwordEncoder.encode(senhaGerada));
        usuario.setSenhaTemporariaBoolean(true);

        userRepository.save(usuario);

        String text = "Senha resetada com sucesso </br>" +
                "Sua nova senha é:" + senhaGerada;
        sendEmail(email, "Reset de senha", text);
    }
    public void enviarEmailBoasVindas(String email,UserEntity usuario){
        String text="Boas vindas "+usuario.getNomeCompleto()+" sua conta foi criada com sucesso!";

        sendEmail(email,);
    }
}
