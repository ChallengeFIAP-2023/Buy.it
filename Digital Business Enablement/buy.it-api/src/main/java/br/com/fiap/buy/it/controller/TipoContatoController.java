package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.repository.TipoContatoRepository;

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

@RestController
@RequestMapping("tiposContato")
@Slf4j
public class TipoContatoController {

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @GetMapping
    public Page<TipoContato> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(TipoContato) - Buscando todos(as)");
        return tipoContatoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoContato> findById(@PathVariable Long id) {
        log.info("(TipoContato) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<TipoContato> create(@RequestBody @Valid TipoContato newData) {
        log.info("(TipoContato) - Cadastrando: " + newData);

        TipoContato savedData = tipoContatoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<TipoContato> update(@PathVariable Long id, @RequestBody @Valid TipoContato updatedData) {
        log.info("(TipoContato) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        tipoContatoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TipoContato> delete(@PathVariable Long id) {
        log.info("(TipoContato) - Deletando por ID: " + id);
        tipoContatoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private TipoContato getById(Long id) {
        return tipoContatoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(TipoContato) não encontrado(a) por ID: " + id));
    }
}