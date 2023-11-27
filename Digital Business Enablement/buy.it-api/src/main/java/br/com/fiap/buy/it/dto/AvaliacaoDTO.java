package br.com.fiap.buy.it.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvaliacaoDTO {
    private Long id;
    private Long idCotacao;
    private Date data;
    private Integer notaEntrega;
    private Integer notaQualidade;
    private Integer notaPreco;
    private String descricao;
}