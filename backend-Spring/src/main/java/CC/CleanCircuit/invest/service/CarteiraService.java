package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Optional;

@Service
public class CarteiraService {
    @Autowired
    private InvestidorRepository investidorRepository;


    public ResponseEntity<ApiResponseDTO> retornarCarteira(Long id) {
        Optional<InvestidorEntity> investidor = investidorRepository.findById(id);

        if (investidor.isEmpty()) {
            return ApiResponse.resposta(null, "usuário não encontrado", 404);
        }
        LinkedHashMap<String, Object> carteira = new LinkedHashMap<>();
        carteira.put("Ações", investidor.get().getAcoes());
        carteira.put("Fundos Imobiliários", investidor.get().getFiis());
        carteira.put("ETFs", investidor.get().getEtfs());
        carteira.put("Criptomoedas", investidor.get().getCriptos());
        carteira.put("Reits", investidor.get().getReits());
        return null;
    }

}
