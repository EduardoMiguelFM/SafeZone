package br.com.fiap.SafeZone.repository;

import br.com.fiap.SafeZone.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
}