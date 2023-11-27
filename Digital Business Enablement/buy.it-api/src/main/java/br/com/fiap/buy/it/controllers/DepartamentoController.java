package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Produto;
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
@Slf4j
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/departamentos")
    public ResponseEntity<Page<Departamento>> getAllDepartamentos(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Departamento> departamentos = departamentoRepository.findAll(pageable);
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Departamento não encontrado com ID: " + id));
        return ResponseEntity.ok(departamento);
    }

    @PostMapping("/departamento")
    public ResponseEntity<Departamento> createDepartamento(@RequestBody @Valid Departamento departamento) {
        Set<Tag> tagsValidadas = departamento.getTags().stream()
                .map(tag -> tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tag não encontrada com o ID: " + tag.getId())))
                .collect(Collectors.toSet());

        departamento.setTags(tagsValidadas);
        Departamento savedDepartamento = departamentoRepository.save(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartamento);
    }

    @PutMapping("/departamento/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody @Valid Departamento departamentoDetails) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Departamento não encontrado com ID: " + id));

        departamento.setNome(departamentoDetails.getNome());
        departamento.setIcone(departamentoDetails.getIcone());

        if (departamentoDetails.getTags() != null) {
            Set<Tag> tagsValidadas = departamentoDetails.getTags().stream()
                    .map(tag -> tagRepository.findById(tag.getId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Tag não encontrada com o ID: " + tag.getId())))
                    .collect(Collectors.toSet());

            departamento.setTags(tagsValidadas);
        }

        Departamento updatedDepartamento = departamentoRepository.save(departamento);
        return ResponseEntity.ok(updatedDepartamento);
    }

    @DeleteMapping("/departamento/{id}")
    public ResponseEntity<HttpStatus> deleteDepartamento(@PathVariable Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Departamento não encontrado com ID: " + id));

        departamentoRepository.delete(departamento);
        return ResponseEntity.noContent().build();
    }
}