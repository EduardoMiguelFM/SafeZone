package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localizacoes")
@Tag(name = "Localizações", description = "Cadastro e consulta de regiões e áreas geográficas")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @Operation(summary = "Listar todas as localizações", description = "Retorna todas as localizações registradas")
    @GetMapping
    public List<Localizacao> listarTodas() {
        return localizacaoService.listarTodas();
    }

    @Operation(summary = "Cadastrar nova localização", description = "Salva uma nova região geográfica no sistema")
    @PostMapping
    public Localizacao salvar(@RequestBody Localizacao localizacao) {
        return localizacaoService.salvar(localizacao);
    }

    @Operation(summary = "Buscar localização por ID", description = "Retorna os dados de uma localização específica")
    @GetMapping("/{id}")
    public Localizacao buscarPorId(@PathVariable Long id) {
        return localizacaoService.buscarPorId(id);
    }
}
