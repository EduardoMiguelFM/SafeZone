package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.AlertaEstatisticaDTO;
import br.com.fiap.SafeZone.model.Alerta;
import br.com.fiap.SafeZone.repository.AlertaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas/estatisticas")
@Tag(name = "Estatísticas de Alertas", description = "Indicadores de alertas por tipo, nível e região")
public class EstatisticaController {

    @Autowired
    private AlertaRepository alertaRepository;

    @Operation(summary = "Estatísticas por tipo de desastre", description = "Retorna quantidade de alertas agrupados por tipo (ex: enchente, incêndio, etc)")
    @GetMapping("/por-tipo")
    public List<AlertaEstatisticaDTO> estatisticasPorTipo() {
        List<Alerta> alertas = alertaRepository.findAll();
        return alertas.stream()
                .collect(Collectors.groupingBy(a -> a.getTipoDesastre().name(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new AlertaEstatisticaDTO(e.getKey(), e.getValue()))
                .toList();
    }

    @Operation(summary = "Estatísticas por nível de alerta", description = "Retorna total de alertas por nível (baixo, médio, alto)")
    @GetMapping("/por-nivel")
    public List<AlertaEstatisticaDTO> estatisticasPorNivel() {
        List<Alerta> alertas = alertaRepository.findAll();
        return alertas.stream()
                .collect(Collectors.groupingBy(a -> a.getNivel().name(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new AlertaEstatisticaDTO(e.getKey(), e.getValue()))
                .toList();
    }

    @Operation(summary = "Estatísticas por região", description = "Retorna total de alertas agrupados por região geográfica")
    @GetMapping("/por-regiao")
    public List<AlertaEstatisticaDTO> estatisticasPorRegiao() {
        List<Alerta> alertas = alertaRepository.findAll();
        return alertas.stream()
                .filter(a -> a.getLocalizacao() != null)
                .collect(Collectors.groupingBy(a -> a.getLocalizacao().getRegiao(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new AlertaEstatisticaDTO(e.getKey(), e.getValue()))
                .toList();
    }
}
