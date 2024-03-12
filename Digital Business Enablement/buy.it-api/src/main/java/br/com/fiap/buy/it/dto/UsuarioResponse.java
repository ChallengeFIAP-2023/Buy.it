package br.com.fiap.buy.it.dto;

import br.com.fiap.buy.it.model.Usuario;

public record UsuarioResponse(Long id, String nome, String email) {

    public static UsuarioResponse fromUsuario(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}