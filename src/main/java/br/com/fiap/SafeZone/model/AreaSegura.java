package br.com.fiap.SafeZone.model;


import jakarta.persistence.*;

@Entity
public class AreaSegura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String endereco;
    private String cidade;
    private String estado;
    private Integer capacidade;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Integer getCapacidade() { return capacidade; }

    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public Localizacao getLocalizacao() { return localizacao; }

    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }
}

