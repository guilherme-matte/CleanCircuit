package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.InvestidorEntity;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class InvestidorService {
    @Autowired
    private InvestidorRepository repository;

    public boolean verificarInvestidor(Long id) {
        Optional<InvestidorEntity> investidor = repository.findById(id);
        return investidor.isPresent();//verifica a existencia do investidor
    }
}
