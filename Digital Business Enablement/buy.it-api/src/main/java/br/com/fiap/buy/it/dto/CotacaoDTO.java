package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoDTO {
    private Long id;

    @NotNull(message = "O campo data não pode estar vazio.")
    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataAbertura;

    @NotNull(message = "O campo idComprador não pode estar vazio.")
    private Long idComprador;

    @NotNull(message = "O campo idProduto não pode estar vazio.")
    private Long idProduto;

    @NotNull(message = "O campo quantidadeProduto não pode estar vazio.")
    @Positive
    private BigDecimal quantidadeProduto;

    @NotNull(message = "O campo valorProduto não pode estar vazio.")
    @Positive
    private BigDecimal valorProduto;

    @NotNull(message = "O campo idStatus não pode estar vazio.")
    private Long idStatus;

    @NotNull(message = "O campo prioridadeEntrega não pode estar vazio.")
    @Positive
    @Min(1)
    @Max(3)
    private Long prioridadeEntrega;

    @NotNull(message = "O campo prioridadeQualidade não pode estar vazio.")
    @Positive
    @Min(1)
    @Max(3)
    private Long prioridadeQualidade;

    @NotNull(message = "O campo prioridadePreco não pode estar vazio.")
    @Positive
    @Min(1)
    @Max(3)
    private Long prioridadePreco;

    @NotNull(message = "O campo prazo não pode estar vazio.")
    @PositiveOrZero
    private Long prazo;

    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataFechamento;
}