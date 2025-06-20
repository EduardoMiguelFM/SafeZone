package br.com.fiap.SafeZone.model;

import jakarta.persistence.*;

@Entity
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regiao;
    private String endereco;
    private String cidade;
    private String estado;
    private String coordenadas;

    public Localizacao() {}

    public Localizacao(Long id, String regiao,String endereco, String cidade, String estado, String coordenadas) {
        this.id = id;
        this.regiao = regiao;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.coordenadas = coordenadas;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
