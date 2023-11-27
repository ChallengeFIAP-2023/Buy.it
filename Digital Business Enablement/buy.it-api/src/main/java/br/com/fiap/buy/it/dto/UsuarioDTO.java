package br.com.fiap.buy.it.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO {
    private Long id;
    private String email;
    private String senha;
    private Long idPessoa;
    private Set<Long> idsTags;
}