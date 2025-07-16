package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.dtos.BrapiDTO;
import CC.CleanCircuit.invest.entities.BaseInvestEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class BaseService<T extends BaseInvestEntity> {
    private final BaseInvestRepository<T> repository;
    private final InvestidorRepository investidorRepository;
    private final BrapiService brapiService;

    protected BaseService(BaseInvestRepository<T> repository, InvestidorRepository investidorRepository,
            BrapiService brapiService) {
        this.repository = repository;
        this.investidorRepository = investidorRepository;
        this.brapiService = brapiService;
    }

    @Transactional
    public ResponseEntity<ApiResponseDTO> transacao(@NotNull AtivoDTO dto, String cpf) {
        if (dto.getCotas() <= 0) {
            return ApiResponse.resposta(null, "Quantidade de cotas menor ou igual a zero!", 409);
        }

        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }

        T ativo = repository.findBySiglaAndInvestidor_Cpf(dto.getSigla().toUpperCase(), cpf);

        if (ativo == null) {
            Optional<BrapiDTO> brapi = brapiService.buscarAtivo(dto.getSigla());
            ativo = criarInstanciaVazia();
            ativo.setInvestidor(investidor);
            ativo.setSigla(dto.getSigla().toUpperCase());
            ativo.setNome(brapi.get().getNomeCurto());
            ativo.setCotas(0);
            ativo.setValorTotal(0.0);
            ativo.setDividendos(0.0);
        }

        if (dto.getTipo().equalsIgnoreCase("compra")) {
            ativo.setCotas(ativo.getCotas() + dto.getCotas());
            ativo.setValorTotal(ativo.getValorTotal() + (dto.getCotas() * dto.getValorCota()));
        } else {
            if (ativo.getCotas() <= dto.getCotas()) {
                System.out.println(dto.getSigla() + " - " + investidor.getId());
                repository.deleteBySiglaAndInvestidor_Id(dto.getSigla().trim().toUpperCase(), investidor.getId());
                return ApiResponse.resposta(null,
                        "Ativo " + ativo.getSigla().toUpperCase() + " deletado(vendido) com sucesso!", 202);
            }
            ativo.setCotas(ativo.getCotas() - dto.getCotas());
            ativo.setValorTotal(ativo.getValorTotal() - (dto.getCotas() * dto.getValorCota()));
        }

        repository.save(ativo);
        return ApiResponse.resposta(ativo, dto.getTipo() + " realizada com sucesso!", 200);
    }

    protected abstract T criarInstanciaVazia();
}
