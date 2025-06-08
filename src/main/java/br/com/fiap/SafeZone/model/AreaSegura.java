package br.com.fiap.SafeZone.model;

import jakarta.persistence.*;

@Entity
public class AreaSegura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAbrigo tipo;

    private String endereco;
    private String responsavel;
    private String telefone;
    private Integer capacidade;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoAbrigo getTipo() { return tipo; }
    public void setTipo(TipoAbrigo tipo) { this.tipo = tipo; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }

    public Localizacao getLocalizacao() { return localizacao; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }
}
