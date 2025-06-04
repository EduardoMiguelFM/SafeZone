package br.com.fiap.SafeZone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaResponseDTO {
    private Map<String, Double> main;
    private Map<String, Double> wind;
    private Map<String, Double> rain;
    private Weather[] weather;

    public Map<String, Double> getMain() { return main; }
    public void setMain(Map<String, Double> main) { this.main = main; }

    public Map<String, Double> getWind() { return wind; }
    public void setWind(Map<String, Double> wind) { this.wind = wind; }

    public Map<String, Double> getRain() { return rain; }
    public void setRain(Map<String, Double> rain) { this.rain = rain; }

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] weather) { this.weather = weather; }

    public static class Weather {
        private String main;
        private String description;

        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
