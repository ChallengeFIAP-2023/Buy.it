package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {

    private Long id;
    
    @NotBlank(message = "O campo nome não pode estar vazio.")
    private String nome;
}