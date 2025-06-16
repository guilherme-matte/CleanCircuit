package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.service.CarteiraService;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarteiraController {
    @Autowired
    private CarteiraService service;

    @GetMapping("/carteira/{id}")
    public ResponseEntity<ApiResponseDTO> retornarCarteira(@PathVariable Long id) {
        return service.retornarCarteira(id);
    }

}
