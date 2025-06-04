package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.ClimaAvaliadoDTO;
import br.com.fiap.SafeZone.dto.ClimaResponseDTO;
import br.com.fiap.SafeZone.model.NivelAlerta;
import br.com.fiap.SafeZone.service.ClimaService;
import br.com.fiap.SafeZone.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clima")
@Tag(name = "Avaliação Climática", description = "Cálculo do nível de alerta com base no clima atual")
public class AvaliacaoClimaController {

    @Autowired
    private ClimaService climaService;

    @Autowired
    private AlertaService alertaService;

    @Operation(summary = "Avaliar clima e gerar nível de alerta", description = "Consulta clima atual por cidade e gera nível de alerta (baixo, médio, alto)")
    @GetMapping("/avaliar-alerta/{cidade}")
    public ClimaAvaliadoDTO avaliar(@PathVariable String cidade) {
        ClimaResponseDTO clima = climaService.consultarClima(cidade);
        NivelAlerta nivel = alertaService.calcularNivelAlerta(clima);

        double chuva = clima.getRain() != null ? clima.getRain().getOrDefault("1h", 0.0) : 0.0;
        double vento = clima.getWind() != null ? clima.getWind().getOrDefault("speed", 0.0) : 0.0;
        String descricao = clima.getWeather() != null && clima.getWeather().length > 0
                ? clima.getWeather()[0].getDescription()
                : "Sem descrição";

        return new ClimaAvaliadoDTO(cidade, nivel.name(), chuva, vento, descricao);
    }
}
