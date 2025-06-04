package br.com.fiap.SafeZone.dto;

public class ClimaAvaliadoDTO {
    private String cidade;
    private String nivelAlerta;
    private double chuva;
    private double vento;
    private String descricao;

    public ClimaAvaliadoDTO() {}

    public ClimaAvaliadoDTO(String cidade, String nivelAlerta, double chuva, double vento, String descricao) {
        this.cidade = cidade;
        this.nivelAlerta = nivelAlerta;
        this.chuva = chuva;
        this.vento = vento;
        this.descricao = descricao;
    }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getNivelAlerta() { return nivelAlerta; }
    public void setNivelAlerta(String nivelAlerta) { this.nivelAlerta = nivelAlerta; }

    public double getChuva() { return chuva; }
    public void setChuva(double chuva) { this.chuva = chuva; }

    public double getVento() { return vento; }
    public void setVento(double vento) { this.vento = vento; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
