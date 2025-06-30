package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
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
    @Autowired
    private BrapiService brapiService;

    public ResponseEntity<ApiResponseDTO> transacaoFii(AtivoDTO dto, String cpf) {

        if (dto.getCotas() <= 0) {
            return ApiResponse.resposta(null, "Quantidade de cotas menor ou iguais a zero!", 409);
        }

        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado para o cpf: " + cpf, 404);
        }

        FiiEntity fii = fiiRepository.findBySiglaAndInvestidor_Cpf(dto.getSigla(), cpf);

        if (fii == null) {//caso nao tenha o ativo cadastrado, cadastra o ativo, evita ter que setar investidor nome e sigla toda a vez que o usuario fazer uma transação
            fii = new FiiEntity();
            fii.setInvestidor(investidor);
            fii.setSigla(dto.getSigla().toUpperCase());
            fii.setNome(dto.getSigla().toLowerCase());
            fii.setCotas(0);
            fii.setValorTotal(0.0);
            fii.setDividendos(0.0);
            fiiRepository.save(fii);
        }

        if (dto.getTipo().equalsIgnoreCase("compra")) {
            fii.setCotas(fii.getCotas() + dto.getCotas());
            fii.setValorTotal(fii.getValorTotal() + (dto.getCotas() * dto.getValorCota()));//pega o valor que por cota que veio do front, multiplica pelo quantidade de cotas compradas, e adiciona com o valor presente do banco
        } else {
            if (fii.getCotas() < dto.getCotas()) {//verifica se o usuário está tentando vender mais cotas do que possuí
                return ApiResponse.resposta(null, "Erro: Não é possível vender mais cotas do que possuí", 409);
            }
            fii.setCotas(fii.getCotas() - dto.getCotas());
            fii.setValorTotal(fii.getValorTotal() - (dto.getCotas() * dto.getValorCota()));//pega o valor que por cota que veio do front, multiplica pelo quantidade de cotas compradas, e diminui com o valor presente do banco
        }

        fiiRepository.save(fii);
        return ApiResponse.resposta(fii, dto.getTipo() + " realizada com sucesso!", 200);
    }

    public ResponseEntity<ApiResponseDTO> retornarFiis(String cpf) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);

        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }
        return ApiResponse.resposta(investidor.getFiis(), "Fiis retornadas com sucesso!", 202);
    }
}
