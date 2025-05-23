package CC.CleanCircuit.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private String telefone;

    private String urlProfileImage;

    private String senhaTemporaria;

    private boolean senhaTemporariaBoolean;

    private String resetToken;
    private LocalDateTime resetTokenExpiration;

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(LocalDateTime resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaTemporaria() {
        return senhaTemporaria;
    }

    public void setSenhaTemporaria(String senhaTemporaria) {
        this.senhaTemporaria = senhaTemporaria;
    }

    public boolean isSenhaTemporariaBoolean() {
        return senhaTemporariaBoolean;
    }

    public void setSenhaTemporariaBoolean(boolean senhaTemporariaBoolean) {
        this.senhaTemporariaBoolean = senhaTemporariaBoolean;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUrlProfileImage() {
        return urlProfileImage;
    }

    public void setUrlProfileImage(String urlProfileImage) {
        this.urlProfileImage = urlProfileImage;
    }
}
