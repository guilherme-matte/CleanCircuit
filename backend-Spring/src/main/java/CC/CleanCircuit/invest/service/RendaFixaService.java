package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.RendaFixaDTO;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.entities.RendaFixaEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.invest.repositories.RendaFixaRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class RendaFixaService {
    @Autowired
    private RendaFixaRepository rendaFixaRepository;
    @Autowired
    private InvestidorRepository investidorRepository;

    private final double cdi = 14.90; // selic - 0,10

    @Transactional
    public ResponseEntity<ApiResponseDTO> cadastrarRendaFixa(String cpf, RendaFixaDTO dto) {

        if (dto.getValorAplicado() <= 0) {
            return ApiResponse.resposta(null, "Valor aplicado deve ser maior que zero!", 400);
        }
        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }

        RendaFixaEntity rendaFixa = new RendaFixaEntity();

        rendaFixa.setCdi(dto.getCdi());
        rendaFixa.setNome(dto.getNome());
        rendaFixa.setEmissor(dto.getEmissor());
        rendaFixa.setDataVencimento(dto.getDataVencimento());
        rendaFixa.setDataAplicacao(dto.getDataAplicacao());
        rendaFixa.setInvestidor(investidor);
        rendaFixa.setValorAplicado(dto.getValorAplicado());
        rendaFixa.setRendimento(0.0);

        rendaFixaRepository.save(rendaFixa);
        return ApiResponse.resposta(rendaFixa, "Renda Fixa cadastrada com sucesso!", 202);
    }

    public void calcularRendimento(LocalDate dia) {
        System.out.println("Processando rotina do dia: " + dia);

        List<RendaFixaEntity> rendas = rendaFixaRepository.findAll();

        for (RendaFixaEntity r : rendas) {
            double taxaAnualTitulo = cdi * (r.getCdi() / 100.0);
            double taxaDiaria = (taxaAnualTitulo / 100.0) / 252.0;
            double rendimento = (r.getValorAplicado() + r.getRendimento()) * taxaDiaria;
            r.setRendimento(r.getRendimento() + rendimento);

        }
        rendaFixaRepository.saveAll(rendas);
    }
}
