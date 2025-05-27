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

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
public class AcaoService {
    @Autowired
    private AcaoRepository repository;
    @Autowired
    private InvestidorRepository investidorRepository;

    public ResponseEntity<ApiResponseDTO> retornarAcao(String sigla, Long id) {
        Optional<AcaoEntity> acao = repository.findBySiglaAndInvestidor_Id(sigla, id);
        return acao.map(acaoEntity -> ApiResponse.resposta(acaoEntity, "Ação encontrada com sucesso", 200))
                .orElseGet(() -> ApiResponse.resposta(null, "Ação não encontrada", 404));
    }

    public ResponseEntity<ApiResponseDTO> cadastrarAcao(AcaoEntity acao, String cpf) {
        Optional<InvestidorEntity> investidor = investidorRepository.findByCpf(cpf);

        if (investidor.isEmpty()) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);

        }
        acao.setInvestidor(investidor.get());
        repository.save(acao);
        return ApiResponse.resposta(acao, "Salvo com sucesso", 200);
    }

}
