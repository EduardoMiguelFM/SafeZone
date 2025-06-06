package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.LocalizacaoRequestDTO;
import br.com.fiap.SafeZone.dto.LocalizacaoResponseDTO;
import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public LocalizacaoResponseDTO salvar(LocalizacaoRequestDTO dto) {
        Localizacao localizacao = modelMapper.map(dto, Localizacao.class);
        return modelMapper.map(repository.save(localizacao), LocalizacaoResponseDTO.class);
    }

    public LocalizacaoResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(loc -> modelMapper.map(loc, LocalizacaoResponseDTO.class))
                .orElse(null);
    }
}
