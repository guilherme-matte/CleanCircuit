package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.BrapiDTO;
import CC.CleanCircuit.invest.dtos.ResumoAtivoDTO;
import CC.CleanCircuit.invest.entities.BaseInvestEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarteiraService {
    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private BrapiService brapiService;


    public ResponseEntity<ApiResponseDTO> retornarCarteira(String cpf) {
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "usuário não encontrado", 404);
        }
        List<ResumoAtivoDTO> acoes = montarResumo(investidor.getAcoes());
        List<ResumoAtivoDTO> fiis = montarResumo(investidor.getFiis());
        List<ResumoAtivoDTO> etfs = montarResumo(investidor.getEtfs());
        List<ResumoAtivoDTO> reits = montarResumo(investidor.getReits());
        List<ResumoAtivoDTO> stocks = montarResumo(investidor.getStocks());
        LinkedHashMap<String, Object> carteira = new LinkedHashMap<>();

        carteira.put("Ações", acoes);
        carteira.put("Fundos Imobiliários", fiis);
        carteira.put("ETFs", etfs);
        carteira.put("Reits", reits);
        carteira.put("Ações Estrangeiras", stocks);


        List<Map<String, Object>> criptoResumo = investidor.getCriptos().stream().map(cripto -> {
            Optional<BrapiDTO> brapi = brapiService.buscarAtivo(cripto.getSigla());
            double precoAtual = brapi.map(BrapiDTO::getPrecoAtual).orElse(0.0);
            double valorMercado = precoAtual * cripto.getQuantidade();

            Map<String, Object> resumo = new HashMap<>();
            resumo.put("sigla", cripto.getSigla());
            resumo.put("quantidade", cripto.getQuantidade());
            resumo.put("total", cripto.getTotal());
            resumo.put("valorMercado", valorMercado);
            resumo.put("precoAtual", precoAtual);
            resumo.put("nome", brapi.map(BrapiDTO::getNomeCurto).orElse(null));

            return resumo;
        }).toList();

        carteira.put("Criptomoedas", criptoResumo);
        double totalAplicado = ((List<ResumoAtivoDTO>) carteira.get("Ações")).stream()
                .mapToDouble(ResumoAtivoDTO::getValorAplicado).sum();

        totalAplicado += ((List<ResumoAtivoDTO>) carteira.get("Fundos Imobiliários")).stream()
                .mapToDouble(ResumoAtivoDTO::getValorAplicado).sum();

        // repete para os demais tipos...

        double totalAtual = ((List<ResumoAtivoDTO>) carteira.get("Ações")).stream()
                .mapToDouble(ResumoAtivoDTO::getValorAtualTotal).sum();

        totalAtual += ((List<ResumoAtivoDTO>) carteira.get("Fundos Imobiliários")).stream()
                .mapToDouble(ResumoAtivoDTO::getValorAtualTotal).sum();

        // repete para os demais tipos...

        carteira.put("Valor Aplicado", totalAplicado);
        carteira.put("Valor Atual", totalAtual);
        double variacao = ((totalAtual - totalAplicado) / totalAplicado) * 100;

        carteira.put("Variacao", variacao);

        return ApiResponse.resposta(carteira, "Carteira carregada com sucesso", 200);
    }


    private <T extends BaseInvestEntity> List<ResumoAtivoDTO> montarResumo(List<T> ativos) {
        return ativos.stream().map(ativo -> {
            Optional<BrapiDTO> brapi = brapiService.buscarAtivo(ativo.getSigla());
            double precoAtual = brapi.map(BrapiDTO::getPrecoAtual).orElse(0.0);

            double valorAplicado = ativo.getValorTotal();
            double lucro = (precoAtual * ativo.getCotas()) - valorAplicado;

            ResumoAtivoDTO dto = new ResumoAtivoDTO();
            dto.setSigla(ativo.getSigla());
            dto.setNome(ativo.getNome());
            dto.setCotas(ativo.getCotas());
            dto.setValorAplicado(valorAplicado);
            dto.setValorAtual(precoAtual);
            dto.setValorAtualTotal(precoAtual * dto.getCotas());
            dto.setDividendos(ativo.getDividendos());
            dto.setLucroPrejuizo(lucro);

            return dto;
        }).collect(Collectors.toList());
    }
}
