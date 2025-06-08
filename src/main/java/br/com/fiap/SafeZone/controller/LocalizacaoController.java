package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.LocalizacaoRequestDTO;
import br.com.fiap.SafeZone.dto.LocalizacaoResponseDTO;
import br.com.fiap.SafeZone.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacoes")
@Tag(name = "Localizações", description = "Cadastro e consulta de regiões e áreas geográficas")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @Operation(
            summary = "Listar localizações com paginação",
            description = "Use filtros por cidade, estado ou região. Ex: ?page=0&size=10&sort=cidade,asc"
    )
    @GetMapping
    public Page<LocalizacaoResponseDTO> listarPaginado(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String regiao,
            @Parameter(hidden = true) Pageable pageable
    ) {
        return service.listarFiltrado(cidade, estado, regiao, pageable);
    }

    @Operation(summary = "Cadastrar nova localização", description = "Salva uma nova região geográfica no sistema")
    @PostMapping
    public LocalizacaoResponseDTO salvar(@RequestBody LocalizacaoRequestDTO dto) {
        return service.salvar(dto);
    }

    @Operation(summary = "Buscar localização por ID", description = "Retorna os dados de uma localização específica")
    @GetMapping("/{id}")
    public LocalizacaoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Atualizar localização", description = "Atualiza os dados de uma localização existente")
    @PutMapping("/{id}")
    public LocalizacaoResponseDTO atualizar(@PathVariable Long id, @RequestBody LocalizacaoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @Operation(summary = "Excluir localização", description = "Remove uma localização pelo ID informado")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
