package CC.CleanCircuit.services;

import CC.CleanCircuit.dtos.EmailDTO;
import CC.CleanCircuit.repositories.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailProducer emailProducer;


    @Async("taskExecutor")
    public void sendEmail(String to, String subject, String text) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailResetSenha(String email, String link) {

        String text = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#121212; padding:40px 0;\">\n" +
                "  <tr>\n" +
                "    <td align=\"center\">\n" +
                "      <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#2e2e3e; padding:30px; border-radius:8px; font-family:Arial, sans-serif; color:#f8f8f2;\">\n" +
                "        <tr>\n" +
                "          <td align=\"center\" style=\"font-size:24px; font-weight:bold; padding-bottom:20px;\">\n" +
                "            Redefinição de Senha\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"font-size:16px; line-height:1.5; padding-bottom:20px;\">\n" +
                "            Olá, você solicitou a redefinição de sua senha. Para criar uma nova, clique no botão abaixo:\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td align=\"center\" style=\"padding:20px 0;\">\n" +
                "            <a href=" + link + " style=\"background-color:#5e60ce; color:#ffffff; text-decoration:none; padding:12px 24px; border-radius:6px; display:inline-block; font-weight:bold;\">\n" +
                "              Criar Nova Senha\n" +
                "            </a>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"font-size:14px; color:#cccccc; text-align:center; padding-top:10px;\">\n" +
                "            Se você não solicitou a redefinição, apenas ignore este e-mail.\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>";
        emailProducer.enviarEmailParaFila(new EmailDTO(email, "Reset de senha", text));

    }

    public void enviarEmailBoasVindas(String email, String nome) {
        try {
            String conteudo = "<table width='100%' cellpadding='0' cellspacing='0' border='0' style='background-color:#121212; padding: 40px 0;'>"
                    + "<tr><td align='center'><table width='600' cellpadding='0' cellspacing='0' border='0' "
                    + "style='background-color:#2e2e3e; padding: 30px; border-radius: 8px; font-family: Arial, sans-serif; color: #f8f8f2;'>"
                    + "<tr><td style='font-size: 26px; font-weight: bold; text-align: center; padding-bottom: 20px;'>"
                    + "Bem-vindo, " + nome + "!"
                    + "</td></tr>"
                    + "<tr><td style='font-size: 16px; line-height: 1.5; padding-bottom: 20px;'>"
                    + "Estamos felizes em tê-lo conosco. Agora você faz parte da nossa plataforma. Explore as funcionalidades e aproveite tudo o que oferecemos!"
                    + "</td></tr>"
                    + "<tr><td align='center' style='padding: 20px 0;'>"
                    + "<a href='http://localhost:8000/menu' style='background-color: #5e60ce; color: #ffffff; text-decoration: none; padding: 12px 24px; border-radius: 6px; display: inline-block;'>"
                    + "Acessar Plataforma"
                    + "</a></td></tr>"
                    + "<tr><td style='font-size: 14px; color: #cccccc; text-align: center;'>"
                    + "Se tiver dúvidas, entre em contato com nossa equipe de suporte."
                    + "</td></tr></table></td></tr></table>";

            emailProducer.enviarEmailParaFila(new EmailDTO(email, "Bem-vindo ao sistema!", conteudo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
