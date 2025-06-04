package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de contas e perfis dos usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar usuários com filtros", description = "Retorna usuários com filtros por cidade, região e perfil (role)")
    @GetMapping
    public Page<Usuario> listarFiltrado(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String regiao,
            @RequestParam(required = false) String role,
            Pageable pageable
    ) {
        return usuarioService.listarFiltrado(cidade, regiao, role, pageable);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @Operation(summary = "Buscar usuário por e-mail", description = "Retorna um usuário com base no e-mail informado")
    @GetMapping("/email/{email}")
    public Usuario buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @Operation(summary = "Cadastrar usuário", description = "Cria uma nova conta de usuário")
    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioService.salvar(usuario);
    }

    @Operation(summary = "Excluir usuário", description = "Deleta o registro de um usuário pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
