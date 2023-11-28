package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.TagDTO;
import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.service.DepartamentoService;
import br.com.fiap.buy.it.service.ProdutoService;
import br.com.fiap.buy.it.service.UsuarioService;
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
@RequestMapping("tags")
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<TagDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return tagService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TagDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(tagService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TagDTO> create(@RequestBody @Valid TagDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(tagService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<TagDTO> update(@PathVariable Long id, @RequestBody @Valid TagDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(tagService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TagDTO convertToDto(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setNome(tag.getNome());
        Set<Long> idsDepartamentos = tag.getDepartamentos().stream()
                .map(Departamento::getId)
                .collect(Collectors.toSet());
        dto.setIdsDepartamentos(idsDepartamentos);
        Set<Long> idsUsuarios = tag.getUsuarios().stream()
                .map(Usuario::getId)
                .collect(Collectors.toSet());
        dto.setIdsUsuarios(idsUsuarios);
        Set<Long> idsProdutos = tag.getProdutos().stream()
                .map(Produto::getId)
                .collect(Collectors.toSet());
        dto.setIdsProdutos(idsProdutos);
        return dto;
    }

    private Tag convertToEntity(TagDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setNome(dto.getNome());
        dto.getIdsDepartamentos().stream().forEach(id -> {
            Departamento departamento = departamentoService.findById(id);
            tag.addDepartamento(departamento);
        });
        dto.getIdsUsuarios().stream().forEach(id -> {
            Usuario usuario = usuarioService.findById(id);
            tag.addUsuario(usuario);
        });
        dto.getIdsProdutos().stream().forEach(id -> {
            Produto produto = produtoService.findById(id);
            tag.addProduto(produto);
        });
        return tag;
    }
}