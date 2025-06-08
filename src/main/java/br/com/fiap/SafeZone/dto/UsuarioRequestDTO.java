package br.com.fiap.SafeZone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,25}$",
            message = "A senha deve ter entre 8 e 25 caracteres, conter ao menos uma letra maiúscula e um número, e não pode ter caracteres especiais.")
    @NotBlank(message = "A senha é obrigatória")
    private String senha;


    @Pattern(
            regexp = "^(\\(?\\d{2}\\)?\\s?)?(9\\d{4})[- ]?(\\d{4})$",
            message = "O telefone deve estar no formato (11) 91234-5678 ou 11912345678"
    )
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;


    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
