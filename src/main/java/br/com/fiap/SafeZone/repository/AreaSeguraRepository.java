package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.AreaSegura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AreaSeguraRepository extends JpaRepository<AreaSegura, Long> {

    Page<AreaSegura> findAll(Pageable pageable);
    Page<AreaSegura> findByCidadeIgnoreCase(String cidade, Pageable pageable);
    Page<AreaSegura> findByEstadoIgnoreCase(String estado, Pageable pageable);
    Page<AreaSegura> findByTipoIgnoreCase(String tipo, Pageable pageable);
    Page<AreaSegura> findByCidadeAndEstadoIgnoreCase(String cidade, String estado, Pageable pageable);
    Page<AreaSegura> findByCidadeAndEstadoAndTipoIgnoreCase(String cidade, String estado, String tipo, Pageable pageable);
}