package br.com.fiap.buy.it.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String marca;
    private String cor;
    private String tamanho;
    private String material;
    private String observacao;
    private Long idDepartamento;
    private Set<Long> idsTags;
}