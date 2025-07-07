package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.BaseFracionadoEntity;
import CC.CleanCircuit.invest.entities.ReitsEntity;
import CC.CleanCircuit.invest.repositories.BaseFracionadoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import org.springframework.stereotype.Service;

@Service
public class ReitsService extends BaseFracionadoService<ReitsEntity>{


    public ReitsService(BaseFracionadoRepository<ReitsEntity> repository, InvestidorRepository investidorRepository) {
        super(repository, investidorRepository);
    }

    @Override
    protected ReitsEntity criarInstanciaVazia() {
        return new ReitsEntity();
    }
}
