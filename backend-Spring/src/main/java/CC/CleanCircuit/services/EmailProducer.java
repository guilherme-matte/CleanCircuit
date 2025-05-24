package CC.CleanCircuit.services;

import CC.CleanCircuit.config.RabbitMQConfig;
import CC.CleanCircuit.dtos.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {
    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEmailParaFila(EmailDTO emailDTO) {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, emailDTO);
    }
}

