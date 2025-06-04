package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    public List<Localizacao> listarTodas() {
        return repository.findAll();
    }

    public Localizacao salvar(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public Localizacao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}