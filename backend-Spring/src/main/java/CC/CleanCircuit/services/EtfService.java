package CC.CleanCircuit.services;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class EtfService {
    @Autowired
    private InvestidorRepository investidorRepository

    public ResponseEntity<ApiResponseDTO> transacaoEtf(String cpf, AtivoDTO dto) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "Usuário não encontrado", 404);
        }
    return null;
    }
}
