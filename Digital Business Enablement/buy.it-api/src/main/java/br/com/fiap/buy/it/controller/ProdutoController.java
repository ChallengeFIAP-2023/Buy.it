package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.ProdutoDTO;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.service.DepartamentoService;
import br.com.fiap.buy.it.service.ProdutoService;
import br.com.fiap.buy.it.service.TagService;

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

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Objects;

@RestController
@RequestMapping("produtos")
@Slf4j
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public Page<ProdutoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return produtoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(produtoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(produtoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody @Valid ProdutoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(produtoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setMarca(produto.getMarca());
        dto.setCor(produto.getCor());
        dto.setTamanho(produto.getTamanho());
        dto.setMaterial(produto.getMaterial());
        dto.setObservacao(produto.getObservacao());
        dto.setIdDepartamento(produto.getDepartamento() != null ? produto.getDepartamento().getId() : null);
        Set<Long> idsTags = produto.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());
        dto.setIdsTags(idsTags);
        return dto;
    }

    private Produto convertToEntity(ProdutoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        produto.setCor(dto.getCor());
        produto.setTamanho(dto.getTamanho());
        produto.setMaterial(dto.getMaterial());
        produto.setObservacao(dto.getObservacao());
        if (dto.getIdDepartamento() != null)
            produto.setDepartamento(departamentoService.findById(dto.getIdDepartamento()));
        dto.getIdsTags().stream().forEach(id -> {
            Tag tag = tagService.findById(id);
            produto.addTag(tag);
        });
        return produto;
    }
}