package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.AlertaRequestDTO;
import br.com.fiap.SafeZone.dto.AlertaResponseDTO;
import br.com.fiap.SafeZone.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertas")
@Tag(name = "Alertas", description = "Gerenciamento de alertas de desastres naturais")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @Operation(
            summary = "Listar alertas com filtros",
            description = "Filtra alertas por cidade, região e nível. Use paginação com ?page=0&size=10&sort=dataOcorrencia,desc"
    )
    @GetMapping
    public Page<AlertaResponseDTO> listarFiltrado(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String regiao,
            @RequestParam(required = false) String nivel,
            @Parameter(hidden = true) Pageable pageable
    ) {
        return alertaService.listarFiltrado(cidade, regiao, nivel, pageable);
    }

    @Operation(summary = "Buscar alerta por ID", description = "Retorna os dados de um alerta específico pelo ID")
    @GetMapping("/{id}")
    public AlertaResponseDTO buscarPorId(@PathVariable Long id) {
        return alertaService.buscarDTO(id);
    }

    @Operation(summary = "Criar novo alerta", description = "Cadastra um novo alerta com dados climáticos automáticos e localização")
    @PostMapping
    public AlertaResponseDTO salvar(@RequestBody AlertaRequestDTO dto) {
        return alertaService.salvarDTO(dto);
    }

    @Operation(summary = "Atualizar alerta existente", description = "Atualiza os dados de um alerta já registrado")
    @PutMapping("/{id}")
    public AlertaResponseDTO atualizar(@PathVariable Long id, @RequestBody AlertaRequestDTO dto) {
        dto.setLocalizacaoId(dto.getLocalizacaoId()); // opcional: reatribuindo por clareza
        AlertaResponseDTO existente = alertaService.buscarDTO(id);
        if (existente == null) return null;
        return alertaService.salvarDTO(dto); // salvarDTO trata se é novo ou não
    }

    @Operation(summary = "Excluir alerta", description = "Remove um alerta pelo ID informado")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alertaService.deletar(id);
    }
}
