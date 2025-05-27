package CC.CleanCircuit.invest.entities;

import CC.CleanCircuit.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_investidor")
public class InvestidorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String cpf;

    @JsonBackReference
    @OneToOne(mappedBy = "investidor")
    private UserEntity user;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcaoEntity> acoes;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FiiEntity> fiis;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EtfEntity> etfs;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriptoEntity> criptos;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReitsEntity> reits;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockEntity> stocks;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RendaFixaEntity> rendasFixas;


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public List<AcaoEntity> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<AcaoEntity> acoes) {
        this.acoes = acoes;
    }

    public List<FiiEntity> getFiis() {
        return fiis;
    }

    public void setFiis(List<FiiEntity> fiis) {
        this.fiis = fiis;
    }

    public List<EtfEntity> getEtfs() {
        return etfs;
    }

    public void setEtfs(List<EtfEntity> etfs) {
        this.etfs = etfs;
    }

    public List<CriptoEntity> getCriptos() {
        return criptos;
    }

    public void setCriptos(List<CriptoEntity> criptos) {
        this.criptos = criptos;
    }

    public List<ReitsEntity> getReits() {
        return reits;
    }

    public void setReits(List<ReitsEntity> reits) {
        this.reits = reits;
    }

    public List<StockEntity> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockEntity> stocks) {
        this.stocks = stocks;
    }

    public List<RendaFixaEntity> getRendasFixas() {
        return rendasFixas;
    }

    public void setRendasFixas(List<RendaFixaEntity> rendasFixas) {
        this.rendasFixas = rendasFixas;
    }
}
