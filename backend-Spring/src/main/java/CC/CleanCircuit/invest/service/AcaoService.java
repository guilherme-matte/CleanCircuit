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

    public ResponseEntity<ApiResponseDTO> retornarAcoes(String cpf) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor ==null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }
        return ApiResponse.resposta(investidor.getAcoes(), "Ações retornadas com sucesso", 200);
    }

    public ResponseEntity<ApiResponseDTO> cadastrarAcao(AcaoEntity acao, String cpf) {

        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor==null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }

        acao.setInvestidor(investidor);

        acaoRepository.save(acao);

        return ApiResponse.resposta(acao, "Acao cadastrada", 200);
    }

    public ResponseEntity<ApiResponseDTO> retornarAcaoUnica(String sigla, Long idInvestidor) {

        if (!investidorService.verificarInvestidor(idInvestidor)) {
            return ApiResponse.resposta(null, "investidor não encontrado", 404);
        }

        Optional<AcaoEntity> acao = acaoRepository.findBySigla(sigla);

        if (acao.isEmpty()) {
            return ApiResponse.resposta(null, "Ação não encontrado", 404);
        }

        return ApiResponse.resposta(acao, "Ação encontrada com sucesso", 200);

    }

}
