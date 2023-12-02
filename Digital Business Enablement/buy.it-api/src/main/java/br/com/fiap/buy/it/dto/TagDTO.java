package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Long id;

    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    private Set<Long> idsDepartamentos;

    private Set<Long> idsUsuarios;

    private Set<Long> idsProdutos;
}