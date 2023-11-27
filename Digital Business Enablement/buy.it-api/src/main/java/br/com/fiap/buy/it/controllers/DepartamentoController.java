package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
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
@RequestMapping("departamentos")
@Slf4j
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public Page<Departamento> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Departamento) - Buscando todos(as)");
        return departamentoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Departamento> findById(@PathVariable Long id) {
        log.info("(Departamento) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Departamento> create(@RequestBody @Valid Departamento newData) {
        log.info("(Departamento) - Cadastrando: " + newData);

        Set<Tag> tags = newData.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Departamento) - Tag não encontrado(a) por ID: " + tag.getId())))
                .collect(Collectors.toSet());
        newData.setTags(tags);

        Departamento savedData = departamentoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Departamento> update(@PathVariable Long id, @RequestBody @Valid Departamento updatedData) {
        log.info("(Departamento) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Set<Tag> tags = updatedData.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Departamento) - Tag não encontrado(a) por ID: " + tag.getId())))
                .collect(Collectors.toSet());
        updatedData.setTags(tags);

        departamentoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Departamento> delete(@PathVariable Long id) {
        log.info("(Departamento) - Deletando por ID: " + id);
        departamentoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Departamento getById(Long id) {
        return departamentoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Departamento) não encontrado(a) por ID: " + id));
    }
}