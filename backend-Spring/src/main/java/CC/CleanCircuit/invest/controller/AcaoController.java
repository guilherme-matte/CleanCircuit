package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.entities.AcaoEntity;
import CC.CleanCircuit.invest.repositories.AcaoRepository;
import CC.CleanCircuit.invest.repositories.InvestidorRepository;
import CC.CleanCircuit.invest.service.AcaoService;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class AcaoController {
    private final AcaoService acaoService;

    public AcaoController(AcaoRepository acaoRepository, InvestidorRepository investidorRepository, AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @GetMapping("/acao/{sigla}/{id}")
    public ResponseEntity<ApiResponseDTO> retornarAcao(@PathVariable String sigla, @PathVariable Long id) {
        return acaoService.retornarAcao(sigla, id);
    }

    @PostMapping("/acao/{cpf}")
    public ResponseEntity<ApiResponseDTO> cadastrarAcao(@RequestBody AcaoEntity acao, @PathVariable String cpf) {
        return acaoService.cadastrarAcao(acao, cpf);
    }
}
