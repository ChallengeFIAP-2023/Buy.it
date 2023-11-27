package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {

    @NotBlank(message = "O endereço de e-mail não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    private String email;

    @NotBlank(message = "A senha não pode estar vazia.")
    private String senha;
}