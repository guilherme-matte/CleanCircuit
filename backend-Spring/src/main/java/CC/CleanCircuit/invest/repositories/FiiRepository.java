package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.FiiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiiRepository extends JpaRepository<FiiEntity, Long> {
    FiiEntity findBySiglaAndInvestidor_Cpf(String sigla, String cpf);

    boolean existsBySiglaAndInvestidor_Cpf(String sigla, String cpf);
}
