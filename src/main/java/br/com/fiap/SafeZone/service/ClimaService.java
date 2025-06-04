package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.ClimaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaService {

    @Value("https://api.openweathermap.org/data/2.5/weather")
    private String apiUrl;

    @Value("b14c19baa3505f3133b7695a2c29457c")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public ClimaResponseDTO consultarClima(String cidade) {
        String url = String.format("%s?q=%s,BR&appid=%s&units=metric", apiUrl, cidade, apiKey);
        return restTemplate.getForObject(url, ClimaResponseDTO.class);
    }
}
