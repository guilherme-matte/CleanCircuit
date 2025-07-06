package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.service.CarteiraService;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarteiraController {
    @Autowired
    private CarteiraService service;

    @GetMapping("/carteira/{cpf}")
    public ResponseEntity<ApiResponseDTO> retornarCarteira(@PathVariable String cpf) {
        return service.retornarCarteira(cpf);
    }

}
