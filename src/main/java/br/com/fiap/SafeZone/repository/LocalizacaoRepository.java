package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.Localizacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    Page<Localizacao> findByCidadeIgnoreCase(String cidade, Pageable pageable);
    Page<Localizacao> findByEstadoIgnoreCase(String estado, Pageable pageable);
    Page<Localizacao> findByRegiaoIgnoreCase(String regiao, Pageable pageable);
    Page<Localizacao> findByCidadeIgnoreCaseAndEstadoIgnoreCase(String cidade, String estado, Pageable pageable);
    Page<Localizacao> findByCidadeIgnoreCaseAndEstadoIgnoreCaseAndRegiaoIgnoreCase(String cidade, String estado, String regiao, Pageable pageable);

}