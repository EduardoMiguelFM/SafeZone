package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.AreaSeguraRequestDTO;
import br.com.fiap.SafeZone.dto.AreaSeguraResponseDTO;
import br.com.fiap.SafeZone.model.AreaSegura;
import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.model.TipoAbrigo;
import br.com.fiap.SafeZone.repository.AreaSeguraRepository;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    public Page<AreaSeguraResponseDTO> listarFiltrado(String cidade, String estado, String tipo, Integer capacidadeMinima, Pageable pageable) {
        Page<AreaSegura> page;
        TipoAbrigo tipoEnum = null;

        if (tipo != null) {
            tipoEnum = TipoAbrigo.fromString(tipo.trim());
        }

        if (cidade != null && estado != null && tipoEnum != null) {
            page = repository.findByLocalizacao_CidadeAndLocalizacao_EstadoAndTipo(cidade, estado, tipoEnum, pageable);
        } else if (cidade != null && estado != null) {
            page = repository.findByLocalizacao_CidadeAndLocalizacao_Estado(cidade, estado, pageable);
        } else if (cidade != null) {
            page = repository.findByLocalizacao_Cidade(cidade, pageable);
        } else if (estado != null) {
            page = repository.findByLocalizacao_Estado(estado, pageable);
        } else if (tipoEnum != null) {
            page = repository.findByTipo(tipoEnum, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        // Filtra em memória
        if (capacidadeMinima != null) {
            page = new PageImpl<>(
                    page.stream()
                            .filter(area -> area.getCapacidade() != null && area.getCapacidade() >= capacidadeMinima)
                            .toList(),
                    pageable,
                    page.getTotalElements()
            );
        }

        return page.map(area -> modelMapper.map(area, AreaSeguraResponseDTO.class));
    }

    public AreaSeguraResponseDTO buscarDTO(Long id) {
        AreaSegura area = repository.findById(id).orElse(null);
        return area != null ? modelMapper.map(area, AreaSeguraResponseDTO.class) : null;
    }

    public AreaSeguraResponseDTO salvarDTO(AreaSeguraRequestDTO dto) {
        AreaSegura area = new AreaSegura();
        area.setNome(dto.getNome());
        area.setCapacidade(dto.getCapacidade());
        area.setResponsavel(dto.getResponsavel());
        area.setTelefone(dto.getTelefone());
        area.setEndereco(dto.getEndereco());

        if (dto.getTipo() != null) {
            area.setTipo(TipoAbrigo.fromString(dto.getTipo()));
        }

        if (dto.getLocalizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId()).orElse(null);
            area.setLocalizacao(localizacao);
        }

        return modelMapper.map(repository.save(area), AreaSeguraResponseDTO.class);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public AreaSeguraResponseDTO atualizarDTO(Long id, AreaSeguraRequestDTO dto) {
        AreaSegura area = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área segura não encontrada com ID: " + id));

        area.setNome(dto.getNome());
        area.setCapacidade(dto.getCapacidade());
        area.setResponsavel(dto.getResponsavel());
        area.setTelefone(dto.getTelefone());
        area.setEndereco(dto.getEndereco());

        if (dto.getTipo() != null) {
            area.setTipo(TipoAbrigo.fromString(dto.getTipo()));
        }

        if (dto.getLocalizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId()).orElse(null);
            area.setLocalizacao(localizacao);
        }

        return modelMapper.map(repository.save(area), AreaSeguraResponseDTO.class);
    }

}
