package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
import br.com.fiap.buy.it.repository.ProdutoRepository;
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
@RequestMapping("produtos")
@Slf4j
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public Page<Produto> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Produto) - Buscando todos(as)");
        return produtoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        log.info("(Produto) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid Produto newData) {
        log.info("(Produto) - Cadastrando: " + newData);

        Departamento departamento = departamentoRepository.findById(newData.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Produto) - Departamento não encontrado(a) por ID: " + newData.getDepartamento().getId()));
        newData.setDepartamento(departamento);

        Set<Tag> tags = newData.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Produto) - Tag não encontrado(a) por ID: " + tag.getId())))
                .collect(Collectors.toSet());
        newData.setTags(tags);

        Produto savedData = produtoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto updatedData) {
        log.info("(Produto) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Departamento departamento = departamentoRepository.findById(updatedData.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Produto) - Departamento não encontrado(a) por ID: " + updatedData.getDepartamento().getId()));
        updatedData.setDepartamento(departamento);

        Set<Tag> tags = updatedData.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Produto) - Tag não encontrado(a) por ID: " + tag.getId())))
                .collect(Collectors.toSet());
        updatedData.setTags(tags);

        produtoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id) {
        log.info("(Produto) - Deletando por ID: " + id);
        produtoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Produto getById(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Produto) não encontrado(a) por ID: " + id));
    }
}