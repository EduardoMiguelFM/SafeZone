package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.UsuarioResponseDTO;
import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsuarioResponseDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(this::toResponseDTO);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        return dto;
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) throw new RuntimeException("Usuário não encontrado");
        return usuario;
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        usuario.setId(id);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Page<UsuarioResponseDTO> listarPorNome(String nome, Pageable pageable) {
        Page<Usuario> page;

        if (nome != null && !nome.isBlank()) {
            page = usuarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            page = usuarioRepository.findAll(pageable);
        }

        return page.map(this::toResponseDTO);
    }

}
