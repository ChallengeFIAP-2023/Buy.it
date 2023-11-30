package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O campo email não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    private String email;

    @NotBlank(message = "O campo senha não pode estar vazio.")
    private String senha;

    @NotNull(message = "O campo idPessoa não pode estar vazio.")
    private Long idPessoa;

    private Set<Long> idsTags;
}