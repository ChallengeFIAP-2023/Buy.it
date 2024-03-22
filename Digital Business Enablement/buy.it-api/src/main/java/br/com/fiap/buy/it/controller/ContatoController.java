package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.ContatoDTO;
import br.com.fiap.buy.it.model.Contato;
import br.com.fiap.buy.it.service.ContatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@RestController
@RequestMapping("contatos")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<Page<Contato>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(contatoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(contatoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody @Valid ContatoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody @Valid ContatoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(contatoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Page<Contato>> findByUsuarioId(@PathVariable Long userId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do usuário: " + userId);
        Page<Contato> contatos = contatoService.findByUsuarioId(userId, pageable);
        return ResponseEntity.ok(contatos);
    }
}