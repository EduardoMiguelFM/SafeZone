package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.AreaSegura;
import br.com.fiap.SafeZone.model.TipoAbrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaSeguraRepository extends JpaRepository<AreaSegura, Long>, JpaSpecificationExecutor<AreaSegura> {
    Page<AreaSegura> findByTipo(TipoAbrigo tipo, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_CidadeAndLocalizacao_EstadoAndTipo(String cidade, String estado, TipoAbrigo tipo, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_CidadeAndLocalizacao_Estado(String cidade, String estado, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_Cidade(String cidade, Pageable pageable);

    Page<AreaSegura> findByLocalizacao_Estado(String estado, Pageable pageable);


}
