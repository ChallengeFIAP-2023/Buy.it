package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.HistoricoDTO;
import br.com.fiap.buy.it.model.Historico;
import br.com.fiap.buy.it.service.HistoricoService;

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
@RequestMapping("historicos")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public ResponseEntity<Page<Historico>> listAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(historicoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Historico> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(historicoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Historico> create(@RequestBody @Valid HistoricoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<Historico> update(@PathVariable Long id, @RequestBody @Valid HistoricoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(historicoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        historicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cotacao/{cotacaoId}")
    public ResponseEntity<Page<Historico>> findByCotacaoId(@PathVariable Long cotacaoId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID da cotacao: " + cotacaoId);
        Page<Historico> historicos = historicoService.findByCotacaoId(cotacaoId, pageable);
        return ResponseEntity.ok(historicos);
    }

    @GetMapping("/usuario/fornecedor/{fornecedorId}")
    public ResponseEntity<Page<Historico>> findByFornecedorId(@PathVariable Long fornecedorId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do fornecedor: " + fornecedorId);
        Page<Historico> historicos = historicoService.findByFornecedorId(fornecedorId, pageable);
        return ResponseEntity.ok(historicos);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<Page<Historico>> findByStatusId(@PathVariable Long statusId, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do status: " + statusId);
        Page<Historico> historicos = historicoService.findByStatusId(statusId, pageable);
        return ResponseEntity.ok(historicos);
    }
}