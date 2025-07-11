package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.invest.dtos.AtivoFracionadoDTO;
import CC.CleanCircuit.invest.service.StockService;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    private StockService service;

    @PostMapping("/stock/{cpf}")
    public ResponseEntity<ApiResponseDTO> transacaoStock(@PathVariable String cpf, @RequestBody AtivoFracionadoDTO dto){
        return service.transacao(dto,cpf);
    }
}
