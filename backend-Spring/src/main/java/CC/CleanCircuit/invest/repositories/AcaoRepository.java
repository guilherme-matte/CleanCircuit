package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcaoRepository extends JpaRepository<AcaoEntity, Long> {
    AcaoEntity findBySiglaAndInvestidor_Cpf(String sigla,String cpf);
}
