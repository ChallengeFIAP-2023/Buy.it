package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.TipoContatoDTO;
import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.service.TipoContatoService;

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
@RequestMapping("tiposContato")
@Slf4j
public class TipoContatoController {

    @Autowired
    private TipoContatoService tipoContatoService;

    @GetMapping
    public ResponseEntity<Page<TipoContato>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(tipoContatoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoContato> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(tipoContatoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoContato> create(@RequestBody @Valid TipoContatoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoContatoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<TipoContato> update(@PathVariable Long id, @RequestBody @Valid TipoContatoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(tipoContatoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        tipoContatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}