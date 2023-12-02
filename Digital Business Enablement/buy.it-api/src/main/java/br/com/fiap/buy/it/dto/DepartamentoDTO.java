package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO {
    
    private Long id;

    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;

    private String icone;
    
    private Set<Long> idsTags;
}