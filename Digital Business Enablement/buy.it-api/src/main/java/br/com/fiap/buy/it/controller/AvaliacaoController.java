package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.AvaliacaoDTO;
import br.com.fiap.buy.it.service.AvaliacaoService;

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
@RequestMapping("avaliacoes")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(avaliacaoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(avaliacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> create(@RequestBody @Valid AvaliacaoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<AvaliacaoDTO> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(avaliacaoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cotacao/{cotacaoId}")
    public ResponseEntity<Page<AvaliacaoDTO>> findByCotacaoId(@PathVariable Long cotacaoId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID da cotacao: " + cotacaoId);
        Page<AvaliacaoDTO> list = avaliacaoService.findByCotacaoId(cotacaoId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<AvaliacaoDTO>> findByUsuarioId(@PathVariable Long usuarioId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do usuario: " + usuarioId);
        Page<AvaliacaoDTO> list = avaliacaoService.findByUsuarioId(usuarioId, pageable);
        return ResponseEntity.ok(list);
    }
}