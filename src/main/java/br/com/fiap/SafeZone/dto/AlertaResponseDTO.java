package br.com.fiap.SafeZone.dto;

import java.time.LocalDateTime;

public class AlertaResponseDTO {
    private Long id;
    private String tipoDesastre;
    private String descricao;
    private String nivel;
    private String regiao;
    private LocalDateTime dataOcorrencia;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoDesastre() { return tipoDesastre; }
    public void setTipoDesastre(String tipoDesastre) { this.tipoDesastre = tipoDesastre; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public LocalDateTime getDataOcorrencia() { return dataOcorrencia; }
    public void setDataOcorrencia(LocalDateTime dataOcorrencia) { this.dataOcorrencia = dataOcorrencia; }
}
