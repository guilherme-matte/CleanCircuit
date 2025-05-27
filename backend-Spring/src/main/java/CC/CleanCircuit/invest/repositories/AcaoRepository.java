package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcaoRepository extends JpaRepository<AcaoEntity, Long> {
    Optional<AcaoEntity> findBySiglaAndInvestidor_Id(String sigla, Long id);
}
