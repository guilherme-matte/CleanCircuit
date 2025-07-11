package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.entities.BaseInvestEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.BaseInvestRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T extends BaseInvestEntity> {
    private final BaseInvestRepository<T> repository;
    private final InvestidorRepository investidorRepository;


    protected BaseService(BaseInvestRepository<T> repository, InvestidorRepository investidorRepository) {
        this.repository = repository;
        this.investidorRepository = investidorRepository;
    }

    public ResponseEntity<ApiResponseDTO> transacao(@NotNull AtivoDTO dto, String cpf) {
        if (dto.getCotas() <= 0) {
            return ApiResponse.resposta(null, "Quantidade de cotas menor ou igual a zero!", 409);
        }

        InvestidorEntity investidor = investidorRepository.findByCpf(cpf);
        if (investidor == null) {
            return ApiResponse.resposta(null, "Investidor não encontrado", 404);
        }

        T ativo = repository.findBySiglaAndInvestidor_Cpf(dto.getSigla(), cpf);

        if (ativo == null) {
            ativo = criarInstanciaVazia();
            ativo.setInvestidor(investidor);
            ativo.setSigla(dto.getSigla().toUpperCase());
            ativo.setNome(dto.getSigla().toLowerCase());
            ativo.setCotas(0);
            ativo.setValorTotal(0.0);
            ativo.setDividendos(0.0);
        }

        if (dto.getTipo().equalsIgnoreCase("compra")) {
            ativo.setCotas(ativo.getCotas() + dto.getCotas());
            ativo.setValorTotal(ativo.getValorTotal() + (dto.getCotas() * dto.getValorCota()));
        } else {
            if (ativo.getCotas() <= dto.getCotas()) {
                repository.delete(ativo);
                return ApiResponse.resposta(null, "Ativo "+ ativo.getSigla().toUpperCase()+" deletado(vendido) com sucesso!", 202);
            }
            ativo.setCotas(ativo.getCotas() - dto.getCotas());
            ativo.setValorTotal(ativo.getValorTotal() - (dto.getCotas() * dto.getValorCota()));
        }

        repository.save(ativo);
        return ApiResponse.resposta(ativo, dto.getTipo() + " realizada com sucesso!", 200);
    }

    protected abstract T criarInstanciaVazia();
}
