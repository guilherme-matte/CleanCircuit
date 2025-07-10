package CC.CleanCircuit.invest.service;

import CC.CleanCircuit.invest.component.BrapiConfig;
import CC.CleanCircuit.invest.dtos.BrapiDTO;
import CC.CleanCircuit.invest.response.BrapiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrapiService {
    private final RestTemplate restTemplate;
    @Autowired
    private BrapiConfig token;
    private final ObjectMapper objectMapper;

    public BrapiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    private String brApi(String sigla) {
        return "https://brapi.dev/api/quote/" + sigla + "?token=" + token.getBrapiToken();
    }

    public List<String> autocompletarSigla(String sigla) {
        try {
            String url = "https://brapi.dev/api/available?search=" + sigla;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode stocks = root.path("stocks");
            List<String> siglas = new ArrayList<>();
            System.out.println(url);
            if (stocks.isArray()) {
                for (JsonNode stock : stocks) {
                    String codigo = stock.asText();
                    System.out.println("Código encontrado: " + codigo);
                    siglas.add(codigo);
                }
            }

            return siglas;
        } catch (Exception e) {
            System.out.println("Erro ao consultar Autocompletar: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Optional<BrapiDTO> buscarAtivo(String sigla) {

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(brApi(sigla), String.class);

            BrapiResponse brapiResponse = objectMapper.readValue(response.getBody(), BrapiResponse.class);

            if (brapiResponse.getResults() != null && !brapiResponse.getResults().isEmpty()) {
                return Optional.of(brapiResponse.getResults().getFirst());
            }

            return Optional.empty();

        } catch (Exception e) {
            System.err.println("Erro ao buscar ativo [" + sigla + "]: " + e.getMessage());
            return Optional.empty();
        }
    }

}
