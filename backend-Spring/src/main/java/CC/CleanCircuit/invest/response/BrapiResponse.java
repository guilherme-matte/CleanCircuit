package CC.CleanCircuit.invest.response;

import CC.CleanCircuit.invest.dtos.BrapiDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BrapiResponse {
    private List<BrapiDTO> results;

    public List<BrapiDTO> getResults() {
        return results;
    }

    public void setResults(List<BrapiDTO> results) {
        this.results = results;
    }
}
