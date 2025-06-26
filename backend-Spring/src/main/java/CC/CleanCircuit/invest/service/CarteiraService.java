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


    public ResponseEntity<ApiResponseDTO> retornarCarteira(String cpf) {
       InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor==null) {
            return ApiResponse.resposta(null, "usuário não encontrado", 404);
        }
        LinkedHashMap<String, Object> carteira = new LinkedHashMap<>();
        carteira.put("Ações", investidor.getAcoes());
        carteira.put("Fundos Imobiliários", investidor.getFiis());
        carteira.put("ETFs", investidor.getEtfs());
        carteira.put("Criptomoedas", investidor.getCriptos());
        carteira.put("Reits", investidor.getReits());
        carteira.put("Ações Estrangeiras", investidor.getStocks());
        System.out.println("teste");
        return ApiResponse.resposta(carteira, "Carteira carregada com sucesso", 200);
    }

}
