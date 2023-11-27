package br.com.fiap.buy.it.dto;

import lombok.*;

import java.util.Date;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CotacaoDTO {
    private Long id;
    private Date dataAbertura;
    private Long idComprador;
    private Long idProduto;
    private BigDecimal quantidadeProduto;
    private BigDecimal valorProduto;
    private Long idStatus;
    private Integer prioridadeEntrega;
    private Integer prioridadeQualidade;
    private Integer prioridadePreco;
    private Integer prazo;
    private Date dataFechamento;
}