package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.repository.PessoaRepository;

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
@RequestMapping("pessoas")
@Slf4j
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public Page<Pessoa> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Pessoa) - Buscando todos(as)");
        return pessoaRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        log.info("(Pessoa) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody @Valid Pessoa newData) {
        log.info("(Pessoa) - Cadastrando: " + newData);

        Pessoa savedData = pessoaRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody @Valid Pessoa updatedData) {
        log.info("(Pessoa) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        pessoaRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable Long id) {
        log.info("(Pessoa) - Deletando por ID: " + id);
        pessoaRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Pessoa getById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Pessoa) não encontrado(a) por ID: " + id));
    }
}