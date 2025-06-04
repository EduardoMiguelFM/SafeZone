package br.com.fiap.SafeZone.model;

import jakarta.persistence.*;

@Entity
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regiao;
    private String cidade;
    private String estado;
    private String coordenadas;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Localizacao() {}

    public Localizacao(Long id, String regiao, String cidade, String estado, String coordenadas, Usuario usuario) {
        this.id = id;
        this.regiao = regiao;
        this.cidade = cidade;
        this.estado = estado;
        this.coordenadas = coordenadas;
        this.usuario = usuario;
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

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
