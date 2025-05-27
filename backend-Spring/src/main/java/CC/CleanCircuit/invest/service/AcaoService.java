package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.repositories.AcaoRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcaoService {
    @Autowired
    private AcaoRepository repository;


    public ResponseEntity<ApiResponseDTO> retornarAcao(String sigla) {
        Optional<AcaoEntity> acao = repository.findBySigla(sigla);
        return acao.map(acaoEntity -> ApiResponse.resposta(acaoEntity, "Ação encontrada com sucesso", 200))
                .orElseGet(() -> ApiResponse.resposta(null, "Ação não encontrada", 404));
    }
}
