package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.DepartamentoDTO;
import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.service.DepartamentoService;
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
@RequestMapping("departamentos")
@Slf4j
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public Page<DepartamentoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return departamentoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartamentoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(departamentoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> create(@RequestBody @Valid DepartamentoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(departamentoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable Long id,
            @RequestBody @Valid DepartamentoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(departamentoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private DepartamentoDTO convertToDto(Departamento departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setNome(departamento.getNome());
        dto.setIcone(departamento.getIcone());
        Set<Long> idsTags = departamento.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());
        dto.setIdsTags(idsTags);
        return dto;
    }

    private Departamento convertToEntity(DepartamentoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Departamento departamento = new Departamento();
        departamento.setId(dto.getId());
        departamento.setNome(dto.getNome());
        departamento.setIcone(dto.getIcone());
        dto.getIdsTags().stream().forEach(id -> {
            Tag tag = tagService.findById(id);
            departamento.addTag(tag);
        });
        return departamento;
    }
}