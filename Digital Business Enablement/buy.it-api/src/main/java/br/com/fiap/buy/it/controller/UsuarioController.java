package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.Credenciais;
import br.com.fiap.buy.it.dto.Token;
import br.com.fiap.buy.it.dto.UsuarioDTO;
import br.com.fiap.buy.it.dto.UsuarioResponse;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.UsuarioRepository;
import br.com.fiap.buy.it.service.TokenService;
import br.com.fiap.buy.it.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(usuarioService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid Usuario newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        newData.setSenha(passwordEncoder.encode(newData.getSenha()));
        usuarioRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.fromUsuario(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody @Valid UsuarioDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(usuarioService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<Page<UsuarioDTO>> findByTagId(@PathVariable Long tagId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID da tag: " + tagId);
        Page<UsuarioDTO> list = usuarioService.findByTagId(tagId, pageable);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Credenciais credenciais){
        log.info("(" + getClass().getSimpleName() + ") - Validando Credenciais: " + credenciais);
        authManager.authenticate(credenciais.toAuthentication());
        return ResponseEntity.ok(tokenService.generateToken(credenciais.email()));
    }
}