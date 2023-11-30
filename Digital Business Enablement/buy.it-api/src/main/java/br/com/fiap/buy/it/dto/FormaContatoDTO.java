package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaContatoDTO {
    
    private Long id;

    @NotNull(message = "O campo idTipoContato não pode estar vazio.")
    private Long idTipoContato;

    @NotBlank(message = "O campo valor não pode estar vazio.")
    private String valor;

    @NotNull(message = "O campo idPessoa não pode estar vazio.")
    private Long idPessoa;
}