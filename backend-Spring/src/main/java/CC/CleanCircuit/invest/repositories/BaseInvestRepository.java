package CC.CleanCircuit.invest.repositories;

import CC.CleanCircuit.invest.entities.BaseInvestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseInvestRepository<T extends BaseInvestEntity> extends JpaRepository<T, Long> {
    T findBySiglaAndInvestidor_Cpf(String sigla, String cpf);
    void deleteBySiglaAndInvestidor_Id(String sigla, Long id);
}
