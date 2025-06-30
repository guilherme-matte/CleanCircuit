package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.AcaoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }
        return ApiResponse.resposta(investidor.getAcoes(), "Ações retornadas com sucesso", 200);
    }

    public ResponseEntity<ApiResponseDTO> transacaoAcao(AtivoDTO dto, String cpf) {

        if (dto.getCotas() <= 0) {
            return ApiResponse.resposta(null, "Quantidade de cotas menor ou iguais a zero!", 409);
        }

        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }
        AcaoEntity acao = acaoRepository.findBySiglaAndInvestidor_Cpf(dto.getSigla(), cpf);

        if (acao == null) {
            acao = new AcaoEntity();
            acao.setInvestidor(investidor);
            acao.setSigla(dto.getSigla().toUpperCase());
            acao.setNome(dto.getSigla().toLowerCase());
            acao.setCotas(0);
            acao.setValorTotal(0.0);
            acao.setDividendos(0.0);
            acaoRepository.save(acao);
        }
        if (dto.getTipo().equalsIgnoreCase("compra")) {
            acao.setCotas(acao.getCotas() + dto.getCotas());
            acao.setValorTotal(acao.getValorTotal() + (dto.getCotas()) * dto.getValorCota());
        } else {
            if (acao.getCotas() < dto.getCotas()) {
                return ApiResponse.resposta(null, "Erro: Não é possível vender mais cotas do que possuí", 409);
            }
            acao.setCotas(acao.getCotas() - dto.getCotas());
            acao.setValorTotal(acao.getValorTotal() - (dto.getCotas()) * dto.getValorCota());
        }
        acaoRepository.save(acao);
        return ApiResponse.resposta(acao, dto.getTipo() + " realizada com sucesso!", 200);
    }

    public ResponseEntity<ApiResponseDTO> retornarAcaoUnica(String sigla, String cpf) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "investidor não encontrado", 404);
        }

        AcaoEntity acao = acaoRepository.findBySiglaAndInvestidor_Cpf(sigla, cpf);

        if (acao == null) {
            return ApiResponse.resposta(null, "Ação não encontrado", 404);
        }

        return ApiResponse.resposta(acao, "Ação encontrada com sucesso", 200);

    }

}
