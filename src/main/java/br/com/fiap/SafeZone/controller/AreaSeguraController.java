package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.AreaSeguraRequestDTO;
import br.com.fiap.SafeZone.dto.AreaSeguraResponseDTO;
import br.com.fiap.SafeZone.service.AreaSeguraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/areas-seguras")
@Tag(name = "Áreas Seguras", description = "Gerenciamento de abrigos e locais seguros")
public class AreaSeguraController {

    @Autowired
    private AreaSeguraService service;

    @Operation(summary = "Listar áreas seguras", description = "Lista todas as áreas seguras com filtros opcionais por cidade, estado e tipo")
    @GetMapping
    public Page<AreaSeguraResponseDTO> listar(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String tipo,
            Pageable pageable
    ) {
        return service.listarFiltrado(cidade, estado, tipo, pageable);
    }

    @Operation(summary = "Buscar área segura por ID", description = "Retorna os dados de uma área segura pelo ID")
    @GetMapping("/{id}")
    public AreaSeguraResponseDTO buscar(@PathVariable Long id) {
        return service.buscarDTO(id);
    }

    @Operation(summary = "Cadastrar nova área segura", description = "Registra uma nova área segura no sistema")
    @PostMapping
    public AreaSeguraResponseDTO salvar(@RequestBody AreaSeguraRequestDTO dto) {
        return service.salvarDTO(dto);
    }

    @Operation(summary = "Excluir área segura", description = "Remove uma área segura existente pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
