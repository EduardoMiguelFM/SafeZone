package br.com.fiap.SafeZone.dto;

public class AlertaEstatisticaDTO {
    private String categoria;
    private Long quantidade;

    public AlertaEstatisticaDTO() {}

    public AlertaEstatisticaDTO(String categoria, Long quantidade) {
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}