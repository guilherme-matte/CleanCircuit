package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.repositories.AcaoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcaoService {
    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private InvestidorRepository investidorRepository;
}
