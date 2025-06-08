package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.AlertaRequestDTO;
import br.com.fiap.SafeZone.dto.AlertaResponseDTO;
import br.com.fiap.SafeZone.dto.ClimaResponseDTO;
import br.com.fiap.SafeZone.model.Alerta;
import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.model.NivelAlerta;
import br.com.fiap.SafeZone.model.TipoDesastre;
import br.com.fiap.SafeZone.model.Usuario;
import br.com.fiap.SafeZone.repository.AlertaRepository;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import br.com.fiap.SafeZone.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClimaService climaService;

    @Autowired
    private ModelMapper modelMapper;

    public Page<AlertaResponseDTO> listarFiltrado(String cidade, String regiao, String nivel, Pageable pageable) {
        Page<Alerta> page;

        if (cidade != null && regiao != null && nivel != null) {
            page = alertaRepository.findByLocalizacaoCidadeIgnoreCaseAndLocalizacaoRegiaoIgnoreCaseAndNivelIgnoreCase(
                    cidade, regiao, NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else if (cidade != null && nivel != null) {
            page = alertaRepository.findByLocalizacaoCidadeIgnoreCaseAndNivelIgnoreCase(
                    cidade, NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else if (regiao != null && nivel != null) {
            page = alertaRepository.findByLocalizacaoRegiaoIgnoreCaseAndNivelIgnoreCase(
                    regiao, NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else if (cidade != null) {
            page = alertaRepository.findByLocalizacaoCidadeIgnoreCase(cidade, pageable);
        } else if (regiao != null) {
            page = alertaRepository.findByLocalizacaoRegiaoIgnoreCase(regiao, pageable);
        } else if (nivel != null) {
            page = alertaRepository.findByNivelIgnoreCase(NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else {
            page = alertaRepository.findAll(pageable);
        }

        return page.map(alerta -> {
            AlertaResponseDTO dto = modelMapper.map(alerta, AlertaResponseDTO.class);
            dto.setRegiao(alerta.getLocalizacao().getRegiao());
            dto.setDataOcorrencia(alerta.getDataOcorrencia());
            return dto;
        });
    }

    public AlertaResponseDTO buscarDTO(Long id) {
        Alerta alerta = alertaRepository.findById(id).orElse(null);
        if (alerta == null) return null;

        AlertaResponseDTO dto = modelMapper.map(alerta, AlertaResponseDTO.class);
        dto.setRegiao(alerta.getLocalizacao().getRegiao());
        dto.setDataOcorrencia(alerta.getDataOcorrencia());
        return dto;
    }

    public AlertaResponseDTO salvarDTO(AlertaRequestDTO dto) {
        Alerta alerta = new Alerta();

        alerta.setTipoDesastre(TipoDesastre.valueOf(dto.getTipoDesastre()));
        alerta.setDescricao(dto.getDescricao());
        alerta.setDataOcorrencia(dto.getDataOcorrencia());

        if (dto.getLocalizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId()).orElse(null);
            alerta.setLocalizacao(localizacao);

            if (localizacao != null && localizacao.getCidade() != null) {
                ClimaResponseDTO clima = climaService.consultarClima(localizacao.getCidade());

                alerta.setNivel(calcularNivelAlerta(clima));
                alerta.setTemperatura(clima.getMain().getOrDefault("temp", null));
                alerta.setCondicaoClimatica(
                        clima.getWeather() != null && clima.getWeather().length > 0
                                ? clima.getWeather()[0].getDescription()
                                : null
                );
            }
        }

        Alerta salvo = alertaRepository.save(alerta);
        AlertaResponseDTO responseDTO = modelMapper.map(salvo, AlertaResponseDTO.class);
        responseDTO.setRegiao(salvo.getLocalizacao().getRegiao());
        responseDTO.setDataOcorrencia(salvo.getDataOcorrencia());
        return responseDTO;
    }


    public NivelAlerta calcularNivelAlerta(ClimaResponseDTO clima) {
        double chuva = clima.getRain() != null ? clima.getRain().getOrDefault("1h", 0.0) : 0.0;
        double vento = clima.getWind() != null ? clima.getWind().getOrDefault("speed", 0.0) : 0.0;

        if (chuva > 100 || vento > 90) return NivelAlerta.ALTO;
        if (chuva > 50 || vento > 60) return NivelAlerta.MEDIO;
        return NivelAlerta.BAIXO;
    }

    public void deletar(Long id) {
        alertaRepository.deleteById(id);
    }
}
