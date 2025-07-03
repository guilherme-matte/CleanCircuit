package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.EtfEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.EtfRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EtfService {
    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private EtfRepository etfRepository;

    public ResponseEntity<ApiResponseDTO> transacaoEtf(String cpf, AtivoDTO dto) {
        if (dto.getCotas() <= 0) {
            return ApiResponse.resposta(null, "Quantidade de cotas menor ou iguais a zero!", 409);

        }
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "Usuário não encontrado", 404);
        }
        EtfEntity etf = etfRepository.findBySiglaAndInvestidor_Cpf(dto.getSigla(), cpf);
        if (etf == null) {
            etf = new EtfEntity();
            etf.setInvestidor(investidor);
            etf.setSigla(dto.getSigla().toUpperCase());
            etf.setNome(dto.getSigla().toLowerCase());
            etf.setCotas(0);
            etf.setDividendos(0.0);
            etf.setValorTotal(0.0);
            etfRepository.save(etf);
        }
        if (dto.getTipo().equalsIgnoreCase("Compra")) {

            etf.setCotas(etf.getCotas() + dto.getCotas());
            etf.setValorTotal(etf.getValorTotal() + (dto.getCotas() * dto.getValorCota()));
        } else {
            if (etf.getCotas() < etf.getCotas()) {
                return ApiResponse.resposta(null, "Erro: Não é possível vender mais cotas do que possuí", 409);

            }
            etf.setCotas(etf.getCotas() - dto.getCotas());
            etf.setValorTotal(etf.getValorTotal() - (dto.getCotas() * dto.getValorCota()));

        }
        etfRepository.save(etf);
        return ApiResponse.resposta(etf, dto.getTipo() + " realizada com sucesso!", 200);
    }

}
