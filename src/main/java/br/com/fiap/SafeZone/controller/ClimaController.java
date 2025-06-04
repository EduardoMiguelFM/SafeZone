package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.ClimaResponseDTO;
import br.com.fiap.SafeZone.service.ClimaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clima")
@Tag(name = "Consulta Climática", description = "Consulta informações climáticas em tempo real")
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @Operation(summary = "Buscar clima atual por cidade", description = "Retorna temperatura, umidade, chuva e vento de uma cidade")
    @GetMapping("/{cidade}")
    public ClimaResponseDTO buscarClima(@PathVariable String cidade) {
        return climaService.consultarClima(cidade);
    }
}
