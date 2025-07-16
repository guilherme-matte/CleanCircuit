package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.BaseFracionadoEntity;
import CC.CleanCircuit.invest.entities.StockEntity;
import CC.CleanCircuit.invest.repositories.BaseFracionadoRepository;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService extends BaseFracionadoService<StockEntity> {


    public StockService(BaseFracionadoRepository<StockEntity> repository, InvestidorRepository investidorRepository,BrapiService brapiService) {
        super(repository, investidorRepository,brapiService);
    }

    @Override
    protected StockEntity criarInstanciaVazia() {
        return new StockEntity();
    }

}
