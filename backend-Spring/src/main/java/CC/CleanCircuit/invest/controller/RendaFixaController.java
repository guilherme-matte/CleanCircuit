package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.RendaFixaDTO;
import CC.CleanCircuit.invest.service.RendaFixaService;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RendaFixaController {

    @Autowired
    private RendaFixaService rendaFixaService;

    @PostMapping("/rendafixa/{cpf}")
    public ResponseEntity<ApiResponseDTO> cadastrarRendaFixa(@PathVariable String cpf, @RequestBody RendaFixaDTO dto) {
        return rendaFixaService.cadastrarRendaFixa(cpf, dto);
    }

}
