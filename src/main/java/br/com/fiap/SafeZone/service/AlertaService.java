package br.com.fiap.SafeZone.service;

import br.com.fiap.SafeZone.dto.ClimaResponseDTO;
import br.com.fiap.SafeZone.model.Alerta;
import br.com.fiap.SafeZone.model.Localizacao;
import br.com.fiap.SafeZone.model.NivelAlerta;
import br.com.fiap.SafeZone.repository.AlertaRepository;
import br.com.fiap.SafeZone.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private ClimaService climaService;

    public Page<Alerta> listarFiltrado(String cidade, String regiao, String nivel, Pageable pageable) {
        if (cidade != null && regiao != null && nivel != null) {
            return alertaRepository.findByLocalizacaoCidadeIgnoreCaseAndLocalizacaoRegiaoIgnoreCaseAndNivelIgnoreCase(
                    cidade, regiao, NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else if (cidade != null && nivel != null) {
            return alertaRepository.findByLocalizacaoCidadeIgnoreCaseAndNivelIgnoreCase(
                    cidade, NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else if (regiao != null && nivel != null) {
            return alertaRepository.findByLocalizacaoRegiaoIgnoreCaseAndNivelIgnoreCase(
                    regiao, NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else if (cidade != null) {
            return alertaRepository.findByLocalizacaoCidadeIgnoreCase(cidade, pageable);
        } else if (regiao != null) {
            return alertaRepository.findByLocalizacaoRegiaoIgnoreCase(regiao, pageable);
        } else if (nivel != null) {
            return alertaRepository.findByNivelIgnoreCase(NivelAlerta.valueOf(nivel.toUpperCase()), pageable);
        } else {
            return alertaRepository.findAll(pageable);
        }
    }

    public Alerta buscarPorId(Long id) {
        return alertaRepository.findById(id).orElse(null);
    }

    public Alerta salvar(Alerta alerta) {
        if (alerta.getLocalizacao() != null && alerta.getLocalizacao().getId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(alerta.getLocalizacao().getId()).orElse(null);
            alerta.setLocalizacao(localizacao);

            if (localizacao != null && localizacao.getCidade() != null) {
                ClimaResponseDTO clima = climaService.consultarClima(localizacao.getCidade());
                NivelAlerta nivel = calcularNivelAlerta(clima);
                alerta.setNivel(nivel);
            }
        }

        return alertaRepository.save(alerta);
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
