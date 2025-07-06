package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.dtos.AtivoFracionadoDTO;
import CC.CleanCircuit.invest.entities.BaseFracionadoEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.BaseFracionadoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseFracionadoService<T extends BaseFracionadoEntity> {
    private final BaseFracionadoRepository<T> repository;
    private final InvestidorRepository investidorRepository;


    public BaseFracionadoService(BaseFracionadoRepository<T> repository, InvestidorRepository investidorRepository) {
        this.repository = repository;
        this.investidorRepository = investidorRepository;
    }

    @Transactional
    public ResponseEntity<ApiResponseDTO> transacao(@NotNull AtivoFracionadoDTO dto, String cpf) {
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
            ativo.setCotas(0.0);
            ativo.setValorTotal(0.0);
            ativo.setDividendos(0.0);
        }

        if (dto.getTipo().equalsIgnoreCase("compra")) {
            ativo.setCotas(ativo.getCotas() + dto.getCotas());
            ativo.setValorTotal(ativo.getValorTotal() + (dto.getCotas() * dto.getValorCota()));
        } else {
            if (ativo.getCotas() <= dto.getCotas()) {
                repository.deleteBySiglaAndInvestidor_Cpf(ativo.getSigla(), cpf);
                return ApiResponse.resposta(null, "Ativo " + ativo.getSigla().toUpperCase() + " deletado(vendido) com sucesso!", 202);
            }
            ativo.setCotas(ativo.getCotas() - dto.getCotas());
            ativo.setValorTotal(ativo.getValorTotal() - (dto.getCotas() * dto.getValorCota()));
        }

        repository.save(ativo);
        return ApiResponse.resposta(ativo, dto.getTipo() + " realizada com sucesso!", 200);
    }

    protected abstract T criarInstanciaVazia();
}
