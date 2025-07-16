package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.EtfEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.EtfRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
