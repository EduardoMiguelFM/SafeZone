package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.Alerta;
import br.com.fiap.SafeZone.model.NivelAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    Page<Alerta> findByLocalizacaoCidadeIgnoreCase(String cidade, Pageable pageable);
    Page<Alerta> findByLocalizacaoRegiaoIgnoreCase(String regiao, Pageable pageable);
    Page<Alerta> findByNivelIgnoreCase(NivelAlerta nivel, Pageable pageable);
    Page<Alerta> findByLocalizacaoCidadeIgnoreCaseAndNivelIgnoreCase(String cidade, NivelAlerta nivel, Pageable pageable);
    Page<Alerta> findByLocalizacaoRegiaoIgnoreCaseAndNivelIgnoreCase(String regiao, NivelAlerta nivel, Pageable pageable);
    Page<Alerta> findByLocalizacaoCidadeIgnoreCaseAndLocalizacaoRegiaoIgnoreCaseAndNivelIgnoreCase(String cidade, String regiao, NivelAlerta nivel, Pageable pageable);


}