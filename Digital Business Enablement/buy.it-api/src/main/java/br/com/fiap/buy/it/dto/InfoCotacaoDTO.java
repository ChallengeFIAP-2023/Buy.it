package br.com.fiap.buy.it.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoCotacaoDTO {

    private BigDecimal minValor;

    private BigDecimal avgValor;

    private BigDecimal maxValor;

    private ProdutoDTO produto;
}