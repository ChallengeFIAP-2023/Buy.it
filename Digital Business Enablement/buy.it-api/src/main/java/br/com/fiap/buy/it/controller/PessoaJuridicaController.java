package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.PessoaJuridicaDTO;
import br.com.fiap.buy.it.service.PessoaJuridicaService;

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
@RequestMapping("pessoasJuridicas")
@Slf4j
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public ResponseEntity<Page<PessoaJuridicaDTO>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(pessoaJuridicaService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaJuridicaDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(pessoaJuridicaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaJuridicaDTO> create(@RequestBody @Valid PessoaJuridicaDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaJuridicaService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaJuridicaDTO> update(@PathVariable Long id, @RequestBody @Valid PessoaJuridicaDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(pessoaJuridicaService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        pessoaJuridicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}