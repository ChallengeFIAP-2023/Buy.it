package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.PessoaRepository;
import br.com.fiap.buy.it.repository.TagRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<Page<Usuario>> getAllUsuarios(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid Usuario usuario) {
        if (usuario.getPessoa() != null && usuario.getPessoa().getId() != null) {
            Pessoa pessoa = pessoaRepository.findById(usuario.getPessoa().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
            usuario.setPessoa(pessoa);
        }

        Set<Tag> tagsValidadas = usuario.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tag não encontrada com o ID: " + tag.getId())))
                .collect(Collectors.toSet());

        usuario.setTags(tagsValidadas);

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }


    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com ID: " + id));

        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());

        if (usuarioDetails.getPessoa() != null && usuarioDetails.getPessoa().getId() != null) {
            Pessoa pessoa = pessoaRepository.findById(usuarioDetails.getPessoa().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
            usuario.setPessoa(pessoa);
        }

        Set<Tag> tagsValidadas = Optional.ofNullable(usuarioDetails.getTags()).orElse(Collections.emptySet()).stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tag não encontrada com o ID: " + tag.getId())))
                .collect(Collectors.toSet());

        usuario.setTags(tagsValidadas);

        final Usuario updatedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }




    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id));

        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

}
