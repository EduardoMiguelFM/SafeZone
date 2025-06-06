package br.com.fiap.SafeZone.dto;

import br.com.fiap.SafeZone.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRegisterDTO(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull UserRole role
) {}
