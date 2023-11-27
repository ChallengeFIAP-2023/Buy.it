package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.UsuarioRepository;
import br.com.fiap.buy.it.repository.TagRepository;

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

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public Page<Usuario> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Usuario) - Buscando todos(as)");
        return usuarioRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        log.info("(Usuario) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario newData) {
        log.info("(Usuario) - Cadastrando: " + newData);

        Set<Tag> tags = newData.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Usuario) - Tag não encontrado(a) por ID: " + tag.getId())))
                .collect(Collectors.toSet());
        newData.setTags(tags);

        Usuario savedData = usuarioRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario updatedData) {
        log.info("(Usuario) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Set<Tag> tags = updatedData.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Usuario) - Tag não encontrado(a) por ID: " + tag.getId())))
                .collect(Collectors.toSet());
        updatedData.setTags(tags);

        usuarioRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id) {
        log.info("(Usuario) - Deletando por ID: " + id);
        usuarioRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Usuario) não encontrado(a) por ID: " + id));
    }
}