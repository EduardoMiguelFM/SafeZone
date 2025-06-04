package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listarFiltrado(String cidade, String regiao, String role, Pageable pageable) {
        if (cidade != null && regiao != null && role != null)
            return usuarioRepository.findByLocalizacaoCidadeAndLocalizacaoRegiaoAndRoleIgnoreCase(cidade, regiao, role, pageable);
        if (cidade != null && role != null)
            return usuarioRepository.findByLocalizacaoCidadeAndRoleIgnoreCase(cidade, role, pageable);
        if (regiao != null && role != null)
            return usuarioRepository.findByLocalizacaoRegiaoAndRoleIgnoreCase(regiao, role, pageable);
        if (cidade != null)
            return usuarioRepository.findByLocalizacaoCidadeIgnoreCase(cidade, pageable);
        if (regiao != null)
            return usuarioRepository.findByLocalizacaoRegiaoIgnoreCase(regiao, pageable);
        if (role != null)
            return usuarioRepository.findByRoleIgnoreCase(role, pageable);

        return usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
