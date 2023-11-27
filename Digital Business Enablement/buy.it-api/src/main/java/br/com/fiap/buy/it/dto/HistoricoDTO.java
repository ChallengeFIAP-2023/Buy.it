package br.com.fiap.buy.it.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistoricoDTO {
    private Long id;
    private Long idCotacao;
    private Long idFornecedor;
    private Long idStatus;
    private Boolean recusadoPorProduto;
    private Boolean recusadoPorQuantidade;
    private Boolean recusadoPorPreco;
    private Boolean recusadoPorPrazo;
    private String descricao;
    private Date data;
    private BigDecimal valorOfertado;
}