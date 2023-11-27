package br.com.fiap.buy.it.controllers;

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

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
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

    @GetMapping("/tags")
    public ResponseEntity<Page<Tag>> getAllTags(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tag não encontrada com ID: " + id));
        return ResponseEntity.ok(tag);
    }

    @PostMapping("/tag")
    public ResponseEntity<Tag> createTag(@RequestBody @Valid Tag tag) {
        Set<Departamento> departamentosValidados = tag.getDepartamentos().stream()
                .map(departamento -> departamentoRepository.findById(departamento.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Departamento não encontrado com o ID: " + departamento.getId())))
                .collect(Collectors.toSet());

        Set<Usuario> usuariosValidados = tag.getUsuarios().stream()
                .map(usuario -> usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Usuario não encontrado com o ID: " + usuario.getId())))
                .collect(Collectors.toSet());

        Set<Produto> produtosValidados = tag.getProdutos().stream()
                .map(produto -> produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado com o ID: " + produto.getId())))
                .collect(Collectors.toSet());

        tag.setDepartamentos(departamentosValidados);
        tag.setUsuarios(usuariosValidados);
        tag.setProdutos(produtosValidados);

        Tag savedTag = tagRepository.save(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    @PutMapping("/tag/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody @Valid Tag tagDetails) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag não encontrada com ID: " + id));

        Set<Departamento> departamentosValidados = Optional.ofNullable(tagDetails.getDepartamentos()).orElse(Collections.emptySet()).stream()
                .map(departamento -> departamentoRepository.findById(departamento.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Departamento não encontrado com o ID: " + departamento.getId())))
                .collect(Collectors.toSet());

        Set<Usuario> usuariosValidados = Optional.ofNullable(tagDetails.getUsuarios()).orElse(Collections.emptySet()).stream()
                .map(usuario -> usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Usuario não encontrado com o ID: " + usuario.getId())))
                .collect(Collectors.toSet());

        Set<Produto> produtosValidados = Optional.ofNullable(tagDetails.getProdutos()).orElse(Collections.emptySet()).stream()
                .map(produto -> produtoRepository.findById(produto.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado com o ID: " + produto.getId())))
                .collect(Collectors.toSet());

        tag.setNome(tagDetails.getNome());
        tag.setDepartamentos(departamentosValidados);
        tag.setUsuarios(usuariosValidados);
        tag.setProdutos(produtosValidados);

        final Tag updatedTag = tagRepository.save(tag);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tag não encontrada com ID: " + id));

        tagRepository.delete(tag);
        return ResponseEntity.noContent().build();
    }
}