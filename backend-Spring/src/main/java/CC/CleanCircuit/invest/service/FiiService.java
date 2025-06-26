package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.FiiEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.FiiRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FiiService {
    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private FiiRepository fiiRepository;

    public ResponseEntity<ApiResponseDTO> cadFii(FiiEntity fii, String cpf) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado para o cpf: " + cpf, 404);
        }

        fii.setInvestidor(investidor);
        fiiRepository.save(fii);
        return ApiResponse.resposta(fii, "Fundo imobiliário cadastrado com sucesso!", 202);
    }

    public ResponseEntity<ApiResponseDTO> retornarFiis(String cpf) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }
        return ApiResponse.resposta(investidor.getFiis(), "Fiis retornadas com sucesso!", 202);
    }
}
