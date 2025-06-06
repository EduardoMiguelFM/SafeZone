package br.com.fiap.SafeZone.controller;

import br.com.fiap.SafeZone.dto.UsuarioRequestDTO;
import br.com.fiap.SafeZone.dto.UsuarioResponseDTO;
import br.com.fiap.SafeZone.model.UserRole;
import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de contas e perfis dos usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return toResponseDTO(usuario);
    }

    @Operation(summary = "Buscar usuário por e-mail", description = "Retorna um usuário com base no e-mail informado")
    @GetMapping("/email/{email}")
    public UsuarioResponseDTO buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return toResponseDTO(usuario);
    }

    @Operation(summary = "Cadastrar usuário", description = "Cria uma nova conta de usuário")
    @PostMapping
    public UsuarioResponseDTO salvar(@RequestBody @Valid UsuarioRequestDTO dto) {
        Usuario usuario = toEntity(dto);
        Usuario salvo = usuarioService.salvar(usuario);
        return toResponseDTO(salvo);
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) {
        Usuario usuario = toEntity(dto);
        Usuario atualizado = usuarioService.atualizar(id, usuario);
        return toResponseDTO(atualizado);
    }

    @Operation(summary = "Excluir usuário", description = "Deleta o registro de um usuário pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }

    // Conversão manual de DTO → Entidade
    private Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(UserRole.valueOf(dto.getRole().toUpperCase()));
        return usuario;
    }

    // Conversão manual de Entidade → DTO
    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }
}
