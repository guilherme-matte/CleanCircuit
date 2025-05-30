package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.service.AcaoService;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AcaoController {
    private final AcaoService acaoService;

    public AcaoController(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @GetMapping("/acao/{id}/{sigla}")
    public ResponseEntity<ApiResponseDTO> retornarAcaoUnica(@PathVariable String sigla, @PathVariable Long id) {
        return acaoService.retornarAcaoUnica(sigla, id);
    }

    @PostMapping("/acao/{id}")
    public ResponseEntity<ApiResponseDTO> cadastrarAcao(@RequestBody AcaoEntity acao, @PathVariable Long id) {
        return acaoService.cadastrarAcao(acao, id);
    }

    @GetMapping("/acao/{id}")
    public ResponseEntity<ApiResponseDTO> retornarAcoes(@PathVariable Long id) {
        return acaoService.retornarAcaoCarteira(id);
    }
}
