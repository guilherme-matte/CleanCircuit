package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.BaseFracionadoEntity;
import CC.CleanCircuit.invest.entities.BaseInvestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseFracionadoRepository <T extends BaseFracionadoEntity> extends JpaRepository<T, Long> {
    T findBySiglaAndInvestidor_Cpf(String sigla, String cpf);
    void deleteBySiglaAndInvestidor_Cpf(String sigla, String cpf);

}
