package br.com.fiap.buy.it.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaJuridicaDTO {
    private Long id;
    private String nome;
    private String urlImagem;
    private String cnpj;
    private Boolean isFornecedor;
}