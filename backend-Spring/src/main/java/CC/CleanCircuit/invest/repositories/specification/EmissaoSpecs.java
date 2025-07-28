package CC.CleanCircuit.invest.repositories.specification;

import CC.CleanCircuit.invest.entities.EmissaoEntity;
import CC.CleanCircuit.invest.enums.Tipo;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmissaoSpecs {
    public static Specification<EmissaoEntity> entreDatas(LocalDate inicio, LocalDate fim) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataEmissao"), inicio, fim));
    }

    public static Specification<EmissaoEntity> porInvestidorCpf(String cpf) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("investidor").get("cpf"), cpf);
    }

    public static Specification<EmissaoEntity> porSigla(String sigla) {
        return (root, query, builder) -> builder.equal(root.get("sigla"), sigla);
    }

    public static Specification<EmissaoEntity> porTipo(Tipo tipo) {
        return (root, query, builder) -> builder.equal(root.get("tipo"), tipo);
    }
}
