package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.LocalizacaoRequestDTO;
import br.com.fiap.SafeZone.dto.LocalizacaoResponseDTO;
import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<LocalizacaoResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(loc -> modelMapper.map(loc, LocalizacaoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Page<LocalizacaoResponseDTO> listarFiltrado(String cidade, String estado, String regiao, Pageable pageable) {
        Page<Localizacao> page;

        if (cidade != null && estado != null && regiao != null) {
            page = repository.findByCidadeIgnoreCaseAndEstadoIgnoreCaseAndRegiaoIgnoreCase(cidade, estado, regiao, pageable);
        } else if (cidade != null && estado != null) {
            page = repository.findByCidadeIgnoreCaseAndEstadoIgnoreCase(cidade, estado, pageable);
        } else if (cidade != null) {
            page = repository.findByCidadeIgnoreCase(cidade, pageable);
        } else if (estado != null) {
            page = repository.findByEstadoIgnoreCase(estado, pageable);
        } else if (regiao != null) {
            page = repository.findByRegiaoIgnoreCase(regiao, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        return page.map(loc -> modelMapper.map(loc, LocalizacaoResponseDTO.class));
    }

    public LocalizacaoResponseDTO salvar(LocalizacaoRequestDTO dto) {
        Localizacao localizacao = modelMapper.map(dto, Localizacao.class);
        return modelMapper.map(repository.save(localizacao), LocalizacaoResponseDTO.class);
    }

    public LocalizacaoResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(loc -> modelMapper.map(loc, LocalizacaoResponseDTO.class))
                .orElse(null);
    }

    public LocalizacaoResponseDTO atualizar(Long id, LocalizacaoRequestDTO dto) {
        Localizacao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        modelMapper.map(dto, existente);
        return modelMapper.map(repository.save(existente), LocalizacaoResponseDTO.class);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Localização não encontrada");
        }
        repository.deleteById(id);
    }
}
