package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.SafeZone.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}