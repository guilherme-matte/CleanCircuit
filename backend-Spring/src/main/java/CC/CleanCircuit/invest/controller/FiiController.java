package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.service.FiiService;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FiiController {
    @Autowired
    private FiiService service;


    @PostMapping("/fii/{cpf}")
    public ResponseEntity<ApiResponseDTO> cadFii(@RequestBody AtivoDTO dto, @PathVariable String cpf) {
        return service.transacao(dto, cpf);
    }

    @GetMapping("/fii/{cpf}")
    public ResponseEntity<ApiResponseDTO> retornarFiis(@PathVariable String cpf) {
        return null;
    }
}
