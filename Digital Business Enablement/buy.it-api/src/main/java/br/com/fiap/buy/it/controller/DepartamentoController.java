package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.DepartamentoDTO;
import br.com.fiap.buy.it.service.DepartamentoService;

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
@RequestMapping("departamentos")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<Page<DepartamentoDTO>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(departamentoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> create(@RequestBody @Valid DepartamentoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable Long id, @RequestBody @Valid DepartamentoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(departamentoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<Page<DepartamentoDTO>> findByTagId(@PathVariable Long tagId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID da tag: " + tagId);
        Page<DepartamentoDTO> list = departamentoService.findByTagId(tagId, pageable);
        return ResponseEntity.ok(list);
    }
}