package CC.CleanCircuit.invest.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BrapiConfig {
    @Value("${BRAPI_TOKEN}")
    private String brapiToken;

    public String getBrapiToken() {
        return brapiToken;
    }
}
