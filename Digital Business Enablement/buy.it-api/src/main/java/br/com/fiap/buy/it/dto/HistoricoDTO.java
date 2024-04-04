package br.com.fiap.buy.it.dto;

import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoDTO {

    private Long id;
    
    @NotNull(message = "O campo idCotacao não pode estar vazio.")
    private Long idCotacao;

    @NotNull(message = "O campo idFornecedor não pode estar vazio.")
    private Long idFornecedor;

    @NotNull(message = "O campo idStatus não pode estar vazio.")
    private Long idStatus;

    @NotNull(message = "O campo recusadoPorProduto não pode estar vazio.")
    private Boolean recusadoPorProduto;

    @NotNull(message = "O campo recusadoPorQuantidade não pode estar vazio.")
    private Boolean recusadoPorQuantidade;

    @NotNull(message = "O campo recusadoPorPreco não pode estar vazio.")
    private Boolean recusadoPorPreco;

    @NotNull(message = "O campo recusadoPorPrazo não pode estar vazio.")
    private Boolean recusadoPorPrazo;

    private String descricao;

    @NotNull(message = "O campo data não pode estar vazio.")
    @PastOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date data;

    @Positive
    private BigDecimal valorOfertado;
}