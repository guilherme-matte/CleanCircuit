package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.FiiEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.FiiRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FiiService extends BaseService<FiiEntity>{

    protected FiiService(BaseInvestRepository<FiiEntity> repository, InvestidorRepository investidorRepository) {
        super(repository, investidorRepository);
    }

    @Override
    protected FiiEntity criarInstanciaVazia() {
        return new FiiEntity();
    }
}
