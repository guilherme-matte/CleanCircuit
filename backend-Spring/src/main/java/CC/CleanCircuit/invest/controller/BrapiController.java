package CC.CleanCircuit.invest.controller;

import CC.CleanCircuit.invest.dtos.BrapiDTO;
import CC.CleanCircuit.invest.service.BrapiService;
import CC.CleanCircuit.response.ApiResponse;
import CC.CleanCircuit.response.ApiResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BrapiController {
    @Autowired
    private BrapiService service;

    @GetMapping("/brapi/{sigla}")
    public ResponseEntity<ApiResponseDTO> retornarAtivo(@PathVariable String sigla) {
        Optional<BrapiDTO> ativo = service.buscarAtivo(sigla);
        if (ativo.isEmpty()) {
            return ApiResponse.resposta(null, "Ativo não encontrado", 404);
        }
        return ApiResponse.resposta(ativo, "Ativo retornado com sucesso!", 200);
    }
    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @GetMapping("/brapi/autocomplete/{sigla}")
    public ResponseEntity<ApiResponseDTO> autocompleteSigla(@PathVariable String sigla) {
        return ApiResponse.resposta(service.autocompletarSigla(sigla), "Auto completar retornado com sucesso!", 200);
    }
}
