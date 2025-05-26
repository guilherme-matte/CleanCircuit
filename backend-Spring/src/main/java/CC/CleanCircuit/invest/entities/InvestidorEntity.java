package CC.CleanCircuit.invest.entities;

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

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcaoEntity> acao;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FiiEntity> fii;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EtfEntity> etf;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CriptoEntity> cripto;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReitsEntity> reits;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockEntity> stock;

    @OneToMany(mappedBy = "investidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RendaFixaEntity> rendaFixa;
}
