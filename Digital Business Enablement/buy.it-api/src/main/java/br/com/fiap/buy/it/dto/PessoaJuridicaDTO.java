package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaDTO {

    private Long id;

    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    private String urlImagem;

    @NotBlank(message = "O campo cnpj não pode estar vazio.")
    private String cnpj;

    @NotNull(message = "O campo isFornecedor não pode estar vazio.")
    private Boolean isFornecedor;
}