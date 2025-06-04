package br.com.fiap.SafeZone.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank String login,
        @NotBlank String senha
) {
}