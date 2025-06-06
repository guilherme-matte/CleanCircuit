package CC.CleanCircuit.invest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_cripto")
public class CriptoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sigla;
    private double quantidade;
    private double total;

    @ManyToOne
    @JoinColumn(name = "investidor_id", referencedColumnName = "id")
    @JsonBackReference
    private InvestidorEntity investidor;

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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public InvestidorEntity getInvestidor() {
        return investidor;
    }

    public void setInvestidor(InvestidorEntity investidor) {
        this.investidor = investidor;
    }
}
