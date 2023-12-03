package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotNull(message = "O campo nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "O campo email não pode estar vazio.")
    @Email(message = "Endereço de e-mail inválido.")
    private String email;

    @NotBlank(message = "O campo senha não pode estar vazio.")
    private String senha;

    @NotNull(message = "O campo urlImagem não pode estar vazio.")
    private String urlImagem;

    @NotNull(message = "O campo cnpj não pode estar vazio.")
    private String cnpj;

    @NotNull(message = "O campo isFornecedor não pode estar vazio.")
    private Boolean isFornecedor;

    private Set<Long> idsTags;
}