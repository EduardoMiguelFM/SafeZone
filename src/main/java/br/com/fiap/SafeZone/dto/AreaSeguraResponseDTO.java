package br.com.fiap.SafeZone.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AreaSeguraResponseDTO {

    private Long id;
    private String nome;
    private String tipo;
    private String endereco;
    private String responsavel;
    private String telefone;
    private Integer capacidade;

    private String cidade;
    private String estado;
    private Long localizacaoId;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Long localizacaoId) { this.localizacaoId = localizacaoId; }
}
