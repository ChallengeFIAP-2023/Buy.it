package br.com.fiap.buy.it.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagDTO {
    private Long id;
    private String nome;
    private Set<Long> idsDepartamentos;
    private Set<Long> idsUsuarios;
    private Set<Long> idsProdutos;
}