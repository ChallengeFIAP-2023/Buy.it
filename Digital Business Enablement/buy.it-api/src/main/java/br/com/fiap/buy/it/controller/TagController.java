package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.TagDTO;
import br.com.fiap.buy.it.service.TagService;

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
@RequestMapping("tags")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<Page<TagDTO>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(tagService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<TagDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TagDTO> create(@RequestBody @Valid TagDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<TagDTO> update(@PathVariable Long id, @RequestBody @Valid TagDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(tagService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<Page<TagDTO>> findByDepartamentoId(@PathVariable Long departamentoId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do departamento: " + departamentoId);
        Page<TagDTO> tags = tagService.findByDepartamentoId(departamentoId, pageable);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<TagDTO>> findByUsuarioId(@PathVariable Long usuarioId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do usuario: " + usuarioId);
        Page<TagDTO> tags = tagService.findByUsuarioId(usuarioId, pageable);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Page<TagDTO>> findByProdutoId(@PathVariable Long produtoId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do produto: " + produtoId);
        Page<TagDTO> tags = tagService.findByProdutoId(produtoId, pageable);
        return ResponseEntity.ok(tags);
    }
}