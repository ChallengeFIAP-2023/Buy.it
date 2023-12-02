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
public class ContatoController {

    @Autowired
    private ContatoService formaContatoService;

    @GetMapping
    public ResponseEntity<Page<Contato>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(formaContatoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(formaContatoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody @Valid ContatoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(formaContatoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody @Valid ContatoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(formaContatoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        formaContatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}