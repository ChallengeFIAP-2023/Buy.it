package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.ContatoDTO;
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
    public ResponseEntity<Page<ContatoDTO>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(contatoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ContatoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(contatoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> create(@RequestBody @Valid ContatoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<ContatoDTO> update(@PathVariable Long id, @RequestBody @Valid ContatoDTO updatedData) {
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
    public ResponseEntity<Page<ContatoDTO>> findByUsuarioId(@PathVariable Long userId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do usuário: " + userId);
        Page<ContatoDTO> list = contatoService.findByUsuarioId(userId, pageable);
        return ResponseEntity.ok(list);
    }
}