package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.InvestidorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestidorRepository extends JpaRepository<InvestidorEntity, Long> {

Optional<InvestidorEntity> findByUser_Id(Long id);
}
