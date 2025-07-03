package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.EtfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtfRepository extends JpaRepository<EtfEntity, Long> {
    EtfEntity findBySiglaAndInvestidor_Cpf(String sigla, String cpf);
}
