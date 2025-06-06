package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.model.Alerta;
import br.com.fiap.SafeZone.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Listar alertas com filtros", description = "Retorna uma lista paginada de alertas com filtros opcionais por cidade, região e nível")
    @GetMapping
    public Page<Alerta> listarFiltrado(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String regiao,
            @RequestParam(required = false) String nivel,
            Pageable pageable
    ) {
        return alertaService.listarFiltrado(cidade, regiao, nivel, pageable);
    }

    @Operation(summary = "Buscar alerta por ID", description = "Retorna os dados de um alerta específico pelo ID")
    @GetMapping("/{id}")
    public Alerta buscarPorId(@PathVariable Long id) {
        return alertaService.buscarPorId(id);
    }

    @Operation(summary = "Criar novo alerta", description = "Cadastra um novo alerta com dados climáticos automáticos")
    @PostMapping
    public Alerta salvar(@RequestBody Alerta alerta) {
        return alertaService.salvar(alerta);
    }

    @Operation(summary = "Atualizar alerta existente", description = "Atualiza os dados de um alerta já registrado")
    @PutMapping("/{id}")
    public Alerta atualizar(@PathVariable Long id, @RequestBody Alerta alerta) {
        alerta.setId(id);
        return alertaService.salvar(alerta);
    }

    @Operation(summary = "Excluir alerta", description = "Remove um alerta pelo ID informado")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alertaService.deletar(id);
    }
}
