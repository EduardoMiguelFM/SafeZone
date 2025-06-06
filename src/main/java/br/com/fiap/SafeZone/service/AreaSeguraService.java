package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.AreaSeguraRequestDTO;
import br.com.fiap.SafeZone.dto.AreaSeguraResponseDTO;
import br.com.fiap.SafeZone.model.AreaSegura;
import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.repository.AreaSeguraRepository;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AreaSeguraService {

    @Autowired
    private AreaSeguraRepository repository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<AreaSeguraResponseDTO> listarFiltrado(String cidade, String estado, String tipo, Pageable pageable) {
        Page<AreaSegura> page;

        if (cidade != null && estado != null && tipo != null) {
            page = repository.findByLocalizacao_CidadeAndLocalizacao_EstadoAndTipoIgnoreCase(cidade, estado, tipo, pageable);
        } else if (cidade != null && estado != null) {
            page = repository.findByLocalizacao_CidadeAndLocalizacao_EstadoIgnoreCase(cidade, estado, pageable);
        } else if (cidade != null) {
            page = repository.findByLocalizacao_CidadeIgnoreCase(cidade, pageable);
        } else if (estado != null) {
            page = repository.findByLocalizacao_EstadoIgnoreCase(estado, pageable);
        } else if (tipo != null) {
            page = repository.findByTipoIgnoreCase(tipo, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        return page.map(area -> modelMapper.map(area, AreaSeguraResponseDTO.class));
    }

    public AreaSeguraResponseDTO buscarDTO(Long id) {
        AreaSegura area = repository.findById(id).orElse(null);
        return area != null ? modelMapper.map(area, AreaSeguraResponseDTO.class) : null;
    }

    public AreaSeguraResponseDTO salvarDTO(AreaSeguraRequestDTO dto) {
        AreaSegura area = modelMapper.map(dto, AreaSegura.class);

        if (dto.getLocalizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId()).orElse(null);
            area.setLocalizacao(localizacao);
        }

        return modelMapper.map(repository.save(area), AreaSeguraResponseDTO.class);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
