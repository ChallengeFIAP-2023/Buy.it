package br.com.fiap.buy.it.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormaContatoDTO {
    private Long id;
    private Long idTipoContato;
    private String valor;
    private Long idPessoa;
}