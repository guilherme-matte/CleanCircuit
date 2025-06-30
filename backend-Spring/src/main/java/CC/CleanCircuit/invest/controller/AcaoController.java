package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
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


    @GetMapping("/acao/{cpf}/{sigla}")
    public ResponseEntity<ApiResponseDTO> retornarAcaoUnica(@PathVariable String cpf, @PathVariable String sigla) {
        return acaoService.retornarAcaoUnica(sigla, cpf);
    }

    @GetMapping("/acao/{cpf}")
    public ResponseEntity<ApiResponseDTO> retornarAcoes(@PathVariable String cpf) {
        return acaoService.retornarAcoes(cpf);
    }

    @PostMapping("/acao/{cpf}")
    public ResponseEntity<ApiResponseDTO> cadAcao(@RequestBody AtivoDTO dto, @PathVariable String cpf) {
        return acaoService.transacaoAcao(dto, cpf);
    }

}
