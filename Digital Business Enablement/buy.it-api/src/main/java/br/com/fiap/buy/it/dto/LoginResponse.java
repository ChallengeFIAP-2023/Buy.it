package br.com.fiap.buy.it.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private UsuarioDTO usuario;
    private String token;
}