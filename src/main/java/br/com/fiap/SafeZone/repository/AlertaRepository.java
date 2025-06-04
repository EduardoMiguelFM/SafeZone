package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    Page<Alerta> findByLocalizacaoRegiaoIgnoreCase(String regiao, Pageable pageable);
}