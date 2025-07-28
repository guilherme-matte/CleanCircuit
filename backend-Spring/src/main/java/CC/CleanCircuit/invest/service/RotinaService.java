package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.entities.RotinaEntity;
import CC.CleanCircuit.invest.repositories.RendaFixaRepository;
import CC.CleanCircuit.invest.repositories.RotinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RotinaService {
    @Autowired
    private RendaFixaRepository rendaFixaRepository;
    @Autowired
    private RotinaRepository rotinaRepository;
    @Autowired
    private RendaFixaService rendaFixaService;

    @Transactional
    public void executarRotina() {
        LocalDate hoje = LocalDate.now();
        RotinaEntity rotina = rotinaRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    RotinaEntity nova = new RotinaEntity();
                    nova.setUltimaExecucao(LocalDate.now().minusDays(1));
                    return rotinaRepository.save(nova);
                });
        LocalDate ultimaExecucao = rotina.getUltimaExecucao();

        if (!ultimaExecucao.isBefore(hoje)) {
            System.out.println("Rotina Já executada hoje");
            return;
        }
        LocalDate dia = ultimaExecucao.plusDays(1);
        while (!dia.isAfter(hoje)) {
            rendaFixaService.calcularRendimento(dia);
            dia = dia.plusDays(1);
        }
        rotina.setUltimaExecucao(hoje);
        rotinaRepository.save(rotina);
    }

}
