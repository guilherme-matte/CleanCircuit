package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.AcaoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcaoService {
    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private InvestidorService investidorService;

    public ResponseEntity<ApiResponseDTO> cadastrarAcao(AcaoEntity acao, Long idInvestidor) {
        if (!investidorService.verificarInvestidor(idInvestidor)) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }
        Optional<InvestidorEntity> investidor = investidorRepository.findById(idInvestidor)
        acao.setInvestidor(investidor.get());

        return ApiResponse.resposta(acao,"Acao cadastradaa",200);
    }
}
