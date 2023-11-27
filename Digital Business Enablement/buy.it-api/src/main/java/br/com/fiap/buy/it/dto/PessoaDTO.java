package br.com.fiap.buy.it.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaDTO {
    private Long id;
    private String nome;
    private String urlImagem;
}