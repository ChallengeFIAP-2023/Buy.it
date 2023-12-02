package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {
    
    private Long id;

    @NotNull(message = "O campo tipo não pode estar vazio.")
    private String tipo;

    @NotBlank(message = "O campo valor não pode estar vazio.")
    private String valor;

    @NotNull(message = "O campo idUsuario não pode estar vazio.")
    private Long idUsuario;
}