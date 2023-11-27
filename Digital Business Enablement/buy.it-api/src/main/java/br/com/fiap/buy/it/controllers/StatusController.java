package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.repository.StatusRepository;

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

@RestController
@RequestMapping("status")
@Slf4j
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public Page<Status> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Status) - Buscando todos(as)");
        return statusRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Status> findById(@PathVariable Long id) {
        log.info("(Status) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Status> create(@RequestBody @Valid Status newData) {
        log.info("(Status) - Cadastrando: " + newData);

        Status savedData = statusRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Status> update(@PathVariable Long id, @RequestBody @Valid Status updatedData) {
        log.info("(Status) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        statusRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Status> delete(@PathVariable Long id) {
        log.info("(Status) - Deletando por ID: " + id);
        statusRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Status getById(Long id) {
        return statusRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Status) não encontrado(a) por ID: " + id));
    }
}