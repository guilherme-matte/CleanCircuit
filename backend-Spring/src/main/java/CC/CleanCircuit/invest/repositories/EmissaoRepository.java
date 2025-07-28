package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.EmissaoEntity;
import CC.CleanCircuit.invest.enums.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface EmissaoRepository extends JpaRepository<EmissaoEntity, Long>, JpaSpecificationExecutor<EmissaoEntity> {
    List<EmissaoEntity> findBySiglaAndInvestidorCpf(String sigla, String cpf);




}
