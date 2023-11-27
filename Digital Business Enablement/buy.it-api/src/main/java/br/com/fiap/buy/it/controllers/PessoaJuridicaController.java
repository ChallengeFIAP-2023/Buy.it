package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.PessoaJuridica;
import br.com.fiap.buy.it.repository.PessoaJuridicaRepository;

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
@RequestMapping("pessoasJuridicas")
@Slf4j
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping
    public Page<PessoaJuridica> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(PessoaJuridica) - Buscando todos(as)");
        return pessoaJuridicaRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaJuridica> findById(@PathVariable Long id) {
        log.info("(PessoaJuridica) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaJuridica> create(@RequestBody @Valid PessoaJuridica newData) {
        log.info("(PessoaJuridica) - Cadastrando: " + newData);

        PessoaJuridica savedData = pessoaJuridicaRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody @Valid PessoaJuridica updatedData) {
        log.info("(PessoaJuridica) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        pessoaJuridicaRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PessoaJuridica> delete(@PathVariable Long id) {
        log.info("(PessoaJuridica) - Deletando por ID: " + id);
        pessoaJuridicaRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private PessoaJuridica getById(Long id) {
        return pessoaJuridicaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(PessoaJuridica) não encontrado(a) por ID: " + id));
    }
}