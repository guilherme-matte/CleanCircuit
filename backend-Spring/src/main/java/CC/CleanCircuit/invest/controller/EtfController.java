package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.AtivoDTO;
import CC.CleanCircuit.response.ApiResponseDTO;
import CC.CleanCircuit.invest.service.EtfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtfController {
    @Autowired
    private EtfService service;

    @PostMapping("/etf/{cpf}")
    public ResponseEntity<ApiResponseDTO> transacaoEtf(@PathVariable String cpf, @RequestBody AtivoDTO dto) {
        return service.transacao(dto,cpf);
    }
}
