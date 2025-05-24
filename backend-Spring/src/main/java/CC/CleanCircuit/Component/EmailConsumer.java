package CC.CleanCircuit.Component;

import CC.CleanCircuit.config.RabbitMQConfig;
import CC.CleanCircuit.dtos.EmailDTO;
import CC.CleanCircuit.services.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private final MailService mailService;

    public EmailConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receberEmailDaFila(EmailDTO emailDTO) {
        mailService.sendEmail(emailDTO.getTo(), emailDTO.getAssunto(), emailDTO.getCorpo());
    }

}
