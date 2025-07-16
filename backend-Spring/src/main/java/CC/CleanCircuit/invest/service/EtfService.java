package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.EtfEntity;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import org.springframework.stereotype.Service;

@Service
public class EtfService extends BaseService<EtfEntity>{

    protected EtfService(BaseInvestRepository<EtfEntity> repository, InvestidorRepository investidorRepository,BrapiService brapiService) {
        super(repository, investidorRepository,brapiService);
    }

    @Override
    protected EtfEntity criarInstanciaVazia() {
        return new EtfEntity();
    }
}
