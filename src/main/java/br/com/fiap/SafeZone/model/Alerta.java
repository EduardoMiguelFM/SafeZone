package br.com.fiap.SafeZone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDesastre tipoDesastre;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    private NivelAlerta nivel;

    private LocalDateTime dataOcorrencia;

    private Double temperatura;
    private String condicaoClimatica;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoDesastre getTipoDesastre() { return tipoDesastre; }
    public void setTipoDesastre(TipoDesastre tipoDesastre) { this.tipoDesastre = tipoDesastre; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public NivelAlerta getNivel() { return nivel; }
    public void setNivel(NivelAlerta nivel) { this.nivel = nivel; }

    public LocalDateTime getDataOcorrencia() { return dataOcorrencia; }
    public void setDataOcorrencia(LocalDateTime dataOcorrencia) { this.dataOcorrencia = dataOcorrencia; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public String getCondicaoClimatica() { return condicaoClimatica; }
    public void setCondicaoClimatica(String condicaoClimatica) { this.condicaoClimatica = condicaoClimatica; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Localizacao getLocalizacao() { return localizacao; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }
}
