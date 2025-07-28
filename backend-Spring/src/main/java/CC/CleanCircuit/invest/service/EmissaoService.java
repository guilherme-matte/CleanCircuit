package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.EmissaoEntity;
import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.enums.Tipo;
import CC.CleanCircuit.invest.repositories.EmissaoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.invest.repositories.specification.EmissaoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmissaoService {

    @Autowired
    private InvestidorRepository investidorRepository;
    @Autowired
    private EmissaoRepository emissaoRepository;

    public List<EmissaoEntity> buscar(LocalDate inicio, LocalDate fim, String cpf, String sigla, Tipo tipo) {
        Specification<EmissaoEntity> spec = Specification.where(EmissaoSpecs.porInvestidorCpf(cpf));//query básica para pesquisar todos os ativos do usuário, caso todos os outros critérios estejam vazios;
        if (inicio != null && fim != null) {
            spec = spec.and(EmissaoSpecs.entreDatas(inicio, fim));
        }
        if (tipo != null) {
            spec = spec.and(EmissaoSpecs.porTipo(tipo));
        }
        if (sigla != null && sigla.isEmpty() && sigla.isBlank()) {
            spec = spec.and(EmissaoSpecs.porSigla(sigla));
        }
        return emissaoRepository.findAll(spec);
    }

    public void cadastrarEmissao(String sigla, LocalDate dataAplicacao, Tipo tipo, InvestidorEntity investidor, double cotas) {
        EmissaoEntity emissao = new EmissaoEntity();
        emissao.setCotas(cotas);
        emissao.setInvestidor(investidor);
        emissao.setDataAplicacao(dataAplicacao);
        emissao.setSigla(sigla);
        emissao.setTipo(tipo);
        emissaoRepository.save(emissao);
    }


}
