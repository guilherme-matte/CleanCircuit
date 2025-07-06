package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private InvestidorRepository investidorRepository;
    public ResponseEntity<ApiResponseDTO> transacaoStock(String cpf, AtivoDTO dto) {
        if (dto.getCotas()<=0){
            return ApiResponse.resposta(null, "Quantidade de cotas menor ou iguais a zero!", 409);
        }
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
    return null;
    }
}
