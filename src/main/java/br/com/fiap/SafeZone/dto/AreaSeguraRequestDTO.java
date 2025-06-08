package br.com.fiap.SafeZone.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AreaSeguraRequestDTO {

    private String nome;
    private String endereco;
    private String responsavel;
    private String telefone;
    private Integer capacidade;

    @Schema(description = "Tipo de abrigo: ESCOLA, GINASIO, IGREJA, HOSPITAL")
    private String tipo;

    private Long localizacaoId;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Long getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Long localizacaoId) { this.localizacaoId = localizacaoId; }
}
