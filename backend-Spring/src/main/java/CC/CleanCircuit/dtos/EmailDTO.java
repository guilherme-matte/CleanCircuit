package CC.CleanCircuit.dtos;

import java.io.Serializable;

public class EmailDTO implements Serializable {
    private String to;
    private String assunto;
    private String corpo;

    // Construtor padrão (necessário para desserialização)
    public EmailDTO() {
    }

    public EmailDTO(String to, String assunto, String corpo) {
        this.to = to;
        this.assunto = assunto;
        this.corpo = corpo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
