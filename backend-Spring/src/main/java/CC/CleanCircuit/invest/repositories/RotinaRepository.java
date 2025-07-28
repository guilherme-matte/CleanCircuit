package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.RotinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RotinaRepository extends JpaRepository<RotinaEntity, Long> {
    Optional<RotinaEntity> findFirstByOrderByIdAsc();
}