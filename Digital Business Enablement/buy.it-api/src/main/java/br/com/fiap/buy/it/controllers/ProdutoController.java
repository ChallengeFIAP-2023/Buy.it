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

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/produtos")
    public ResponseEntity<Page<Produto>> getAllProdutos(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado com ID: " + id));
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> createProduto(@RequestBody @Valid Produto produto) {
        Departamento departamento = departamentoRepository.findById(produto.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Departamento não encontrado com o ID: " + produto.getDepartamento().getId()));

        Set<Tag> tagsValidadas = produto.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tag não encontrada com o ID: " + tag.getId())))
                .collect(Collectors.toSet());

        produto.setDepartamento(departamento);
        produto.setTags(tagsValidadas);

        Produto savedProduto = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }


    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody @Valid Produto produtoDetails) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado com ID: " + id));

        Departamento departamento = departamentoRepository.findById(produtoDetails.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Departamento não encontrado com o ID: " + produtoDetails.getDepartamento().getId()));

        Set<Tag> tagsValidadas = Optional.ofNullable(produtoDetails.getTags()).orElse(Collections.emptySet()).stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tag não encontrada com o ID: " + tag.getId())))
                .collect(Collectors.toSet());

        produto.setNome(produtoDetails.getNome());
        produto.setMarca(produtoDetails.getMarca());
        produto.setCor(produtoDetails.getCor());
        produto.setTamanho(produtoDetails.getTamanho());
        produto.setMaterial(produtoDetails.getMaterial());
        produto.setObservacao(produtoDetails.getObservacao());
        produto.setDepartamento(departamento);
        produto.setTags(tagsValidadas);

        final Produto updatedProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }


    @DeleteMapping("/produto/{id}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado com ID: " + id));

        produtoRepository.delete(produto);
        return ResponseEntity.noContent().build();
    }

}

