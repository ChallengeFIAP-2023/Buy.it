package br.com.fiap.buy.it.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartamentoDTO {
    private Long id;
    private String nome;
    private String icone;
    private Set<Long> idsTags;
}