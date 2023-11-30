package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "O campo idCotacao não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    private String email;

    @NotBlank(message = "O campo idCotacao não pode estar vazio.")
    private String senha;
}