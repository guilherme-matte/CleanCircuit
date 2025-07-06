package CC.CleanCircuit.invest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@MappedSuperclass
public class BaseFracionadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String sigla;

    private String nome;

    private double cotas;

    private double valorTotal;

    private double dividendos;

    @ManyToOne
    @JoinColumn(name = "investidor_id", referencedColumnName = "id")
    @JsonBackReference
    private InvestidorEntity investidor;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCotas() {
        return cotas;
    }

    public void setCotas(double cotas) {
        this.cotas = cotas;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDividendos() {
        return dividendos;
    }

    public void setDividendos(double dividendos) {
        this.dividendos = dividendos;
    }

    public InvestidorEntity getInvestidor() {
        return investidor;
    }

    public void setInvestidor(InvestidorEntity investidor) {
        this.investidor = investidor;
    }
}
