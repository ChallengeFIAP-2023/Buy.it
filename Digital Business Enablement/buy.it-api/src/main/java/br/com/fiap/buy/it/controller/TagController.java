package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.repository.TagRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;

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
@RequestMapping("tags")
@Slf4j
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public Page<Tag> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Tag) - Buscando todos(as)");
        return tagRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> findById(@PathVariable Long id) {
        log.info("(Tag) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Tag> create(@RequestBody @Valid Tag newData) {
        log.info("(Tag) - Cadastrando: " + newData);

        Set<Departamento> departamentos = newData.getDepartamentos().stream()
                .map(departamento -> departamentoRepository.findById(departamento.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Tag) - Departamento não encontrado(a) por ID: " + departamento.getId())))
                .collect(Collectors.toSet());
        newData.setDepartamentos(departamentos);

        Set<Usuario> usuarios = newData.getUsuarios().stream()
                .map(usuario -> usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Tag) - Usuario não encontrado(a) por ID: " + usuario.getId())))
                .collect(Collectors.toSet());
        newData.setUsuarios(usuarios);

        Set<Produto> produtos = newData.getProdutos().stream()
                .map(produto -> produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Tag) - Produto não encontrado(a) por ID: " + produto.getId())))
                .collect(Collectors.toSet());
        newData.setProdutos(produtos);

        Tag savedData = tagRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody @Valid Tag updatedData) {
        log.info("(Tag) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Set<Departamento> departamentos = updatedData.getDepartamentos().stream()
                .map(departamento -> departamentoRepository.findById(departamento.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Tag) - Departamento não encontrado(a) por ID: " + departamento.getId())))
                .collect(Collectors.toSet());
        updatedData.setDepartamentos(departamentos);

        Set<Usuario> usuarios = updatedData.getUsuarios().stream()
                .map(usuario -> usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Tag) - Usuario não encontrado(a) por ID: " + usuario.getId())))
                .collect(Collectors.toSet());
        updatedData.setUsuarios(usuarios);

        Set<Produto> produtos = updatedData.getProdutos().stream()
                .map(produto -> produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "(Tag) - Produto não encontrado(a) por ID: " + produto.getId())))
                .collect(Collectors.toSet());
        updatedData.setProdutos(produtos);

        tagRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Tag> delete(@PathVariable Long id) {
        log.info("(Tag) - Deletando por ID: " + id);
        tagRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Tag getById(Long id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Tag) não encontrado(a) por ID: " + id));
    }
}