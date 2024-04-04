package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.ProdutoDTO;
import br.com.fiap.buy.it.service.ProdutoService;

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
@RequestMapping("produtos")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(produtoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody @Valid ProdutoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(produtoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<Page<ProdutoDTO>> findByDepartamentoId(@PathVariable Long departamentoId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do departamento: " + departamentoId);
        Page<ProdutoDTO> list = produtoService.findByDepartamentoId(departamentoId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<Page<ProdutoDTO>> findByTagId(@PathVariable Long tagId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID da tag: " + tagId);
        Page<ProdutoDTO> list = produtoService.findByTagId(tagId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<ProdutoDTO>> findByNomeContainingIgnoreCase(@PathVariable String nome, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por nome do produto contendo: " + nome);
        Page<ProdutoDTO> list = produtoService.findByNomeContainingIgnoreCase(nome, pageable);
        return ResponseEntity.ok(list);
    }
}