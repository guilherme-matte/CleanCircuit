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
    private List<AcaoEntity> acaos;

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
}
