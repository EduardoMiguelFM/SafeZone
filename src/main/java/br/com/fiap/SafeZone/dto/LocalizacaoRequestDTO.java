package br.com.fiap.SafeZone.dto;

public class LocalizacaoRequestDTO {

    private String regiao;
    private String endereco;
    private String cidade;
    private String estado;
    private String coordenadas;

    // Construtor vazio
    public LocalizacaoRequestDTO() {}

    // Getters e Setters
    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
