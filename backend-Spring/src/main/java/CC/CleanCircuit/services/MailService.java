package CC.CleanCircuit.services;

import CC.CleanCircuit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    public void enviarEmailResetSenha(String email, String link) {

        String text = "Olá, sua senha pode ser resetada utilizando o seguinte link: " + link;

        sendEmail(email, "Reset de senha", text);
    }

    public void enviarEmailBoasVindas(String email, String nome) {
        String text = "Boas vindas " + nome + " sua conta foi criada com sucesso!";
        sendEmail(email, "Conta criada com sucesso!", text);
    }
}
