package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.AreaSegura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AreaSeguraRepository extends JpaRepository<AreaSegura, Long> {

    Page<AreaSegura> findAll(Pageable pageable);

    Page<AreaSegura> findByLocalizacao_CidadeIgnoreCase(String cidade, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_EstadoIgnoreCase(String estado, Pageable pageable);

    Page<AreaSegura> findByTipoIgnoreCase(String tipo, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_CidadeAndLocalizacao_EstadoIgnoreCase(String cidade, String estado, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_CidadeAndLocalizacao_EstadoAndTipoIgnoreCase(String cidade, String estado, String tipo, Pageable pageable);
}
