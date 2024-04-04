package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {

    private Long id;

    @NotNull(message = "O campo idCotacao não pode estar vazio.")
    private Long idCotacao;

    @NotNull(message = "O campo data não pode estar vazio.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @PastOrPresent
    private Date data;

    @NotNull(message = "O campo notaEntrega não pode estar vazio.")
    @Positive
    @Min(1) @Max(5)
    private Long notaEntrega;

    @NotNull(message = "O campo notaQualidade não pode estar vazio.")
    @Positive
    @Min(1) @Max(5)
    private Long notaQualidade;

    @NotNull(message = "O campo notaPreco não pode estar vazio.")
    @Positive
    @Min(1) @Max(5)
    private Long notaPreco;

    private String descricao;
}