package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.AtivoFracionadoDTO;
import CC.CleanCircuit.invest.service.ReitsService;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReitsController {

    private final ReitsService service;

    public ReitsController(ReitsService service) {
        this.service = service;
    }

    @PostMapping("/reits/{cpf}")
    public ResponseEntity<ApiResponseDTO> transacaoReits(@PathVariable String cpf, @RequestBody AtivoFracionadoDTO dto) {
        return service.transacao(dto, cpf);
    }
}
