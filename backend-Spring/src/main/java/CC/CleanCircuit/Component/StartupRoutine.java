package CC.CleanCircuit.Component;

import CC.CleanCircuit.invest.service.RotinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRoutine implements CommandLineRunner {
    @Autowired
    private RotinaService rotinaService;

    @Override
    public void run(String... args) throws Exception {
        rotinaService.executarRotina();
    }
}
