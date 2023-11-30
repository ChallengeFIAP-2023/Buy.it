package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.CotacaoDTO;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.service.CotacaoService;

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
@RequestMapping("cotacoes")
@Slf4j
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping
    public ResponseEntity<Page<Cotacao>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(cotacaoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Cotacao> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(cotacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cotacao> create(@RequestBody @Valid CotacaoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotacaoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<Cotacao> update(@PathVariable Long id, @RequestBody @Valid CotacaoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(cotacaoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        cotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}