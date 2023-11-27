package br.com.fiap.buy.it.controllers;
import br.com.fiap.buy.it.model.dto.LoginDTO;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginDTO loginDTO) {
        Optional<Usuario> usuario = usuarioService.validarLogin(loginDTO);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get().getId());
        } else {
            return ResponseEntity.status(401).body("Falha na autenticação: credenciais inválidas.");
        }
    }
}