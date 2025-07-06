package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import org.springframework.stereotype.Service;

@Service
public class AcaoService extends BaseService<AcaoEntity> {


    protected AcaoService(BaseInvestRepository<AcaoEntity> repository, InvestidorRepository investidorRepository) {
        super(repository, investidorRepository);
    }


    @Override
    protected AcaoEntity criarInstanciaVazia() {
        return new AcaoEntity();
    }


}
