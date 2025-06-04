package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.model.AreaSegura;
import br.com.fiap.SafeZone.repository.AreaSeguraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class AreaSeguraService {

    @Autowired
    private AreaSeguraRepository repository;

    public Page<AreaSegura> listarFiltrado(String cidade, String estado, String tipo, Pageable pageable) {
        if (cidade != null && estado != null && tipo != null)
            return repository.findByCidadeAndEstadoAndTipoIgnoreCase(cidade, estado, tipo, pageable);
        if (cidade != null && estado != null)
            return repository.findByCidadeAndEstadoIgnoreCase(cidade, estado, pageable);
        if (cidade != null)
            return repository.findByCidadeIgnoreCase(cidade, pageable);
        if (estado != null)
            return repository.findByEstadoIgnoreCase(estado, pageable);
        if (tipo != null)
            return repository.findByTipoIgnoreCase(tipo, pageable);

        return repository.findAll(pageable);
    }

    public AreaSegura buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AreaSegura salvar(AreaSegura area) {
        return repository.save(area);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}