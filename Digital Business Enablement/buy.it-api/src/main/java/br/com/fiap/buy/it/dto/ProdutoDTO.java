package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    private String marca;

    private String cor;

    private String tamanho;

    private String material;

    private String observacao;

    private Long idDepartamento;

    private Set<Long> idsTags;
}