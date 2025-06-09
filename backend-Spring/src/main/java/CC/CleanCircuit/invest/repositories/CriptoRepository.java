package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.CriptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriptoRepository extends JpaRepository<CriptoEntity, Long> {
}
