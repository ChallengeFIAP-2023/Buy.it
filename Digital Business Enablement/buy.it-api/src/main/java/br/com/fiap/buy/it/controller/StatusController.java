package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.StatusDTO;
import br.com.fiap.buy.it.service.StatusService;

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
@RequestMapping("status")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<Page<StatusDTO>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(statusService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(statusService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StatusDTO> create(@RequestBody @Valid StatusDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(statusService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<StatusDTO> update(@PathVariable Long id, @RequestBody @Valid StatusDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(statusService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        statusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}