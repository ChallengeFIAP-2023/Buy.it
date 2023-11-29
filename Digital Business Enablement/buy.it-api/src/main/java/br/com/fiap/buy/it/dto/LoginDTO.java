package br.com.fiap.buy.it.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    private String email;
    private String senha;
}