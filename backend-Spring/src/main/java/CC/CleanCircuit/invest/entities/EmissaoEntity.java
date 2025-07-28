package CC.CleanCircuit.invest.entities;

import CC.CleanCircuit.invest.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_emissao")
public class EmissaoEntity {
    //serve para calculo de dividendos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sigla;

    private LocalDate dataAplicacao;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private double cotas;

    @ManyToOne
    @JoinColumn(name = "investidor_id", referencedColumnName = "id")
    @JsonBackReference
    private InvestidorEntity investidor;

    public double getCotas() {
        return cotas;
    }

    public void setCotas(double cotas) {
        this.cotas = cotas;
    }

    public InvestidorEntity getInvestidor() {
        return investidor;
    }

    public void setInvestidor(InvestidorEntity investidor) {
        this.investidor = investidor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
