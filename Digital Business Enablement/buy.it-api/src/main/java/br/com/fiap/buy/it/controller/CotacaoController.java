package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.dto.CotacaoDTO;
import br.com.fiap.buy.it.dto.InfoCotacaoDTO;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    // Retornando Objeto ao invés de DTO por solicitação do Front.
    @GetMapping
    public ResponseEntity<Page<Cotacao>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(cotacaoService.listAll(pageable));
    }

    // Retornando Objeto ao invés de DTO por solicitação do Front.
    @GetMapping("{id}")
    public ResponseEntity<Cotacao> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(cotacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CotacaoDTO> create(@RequestBody @Valid CotacaoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotacaoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<CotacaoDTO> update(@PathVariable Long id, @RequestBody @Valid CotacaoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(cotacaoService.update(id, updatedData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        cotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Retornando Objeto ao invés de DTO por solicitação do Front.
    @GetMapping("/usuario/comprador/{userId}")
    public ResponseEntity<Page<Cotacao>> findByCompradorId(@PathVariable Long userId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do usuário: " + userId);
        Page<Cotacao> list = cotacaoService.findByCompradorId(userId, pageable);
        return ResponseEntity.ok(list);
    }

    // Retornando Objeto ao invés de DTO por solicitação do Front.
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Page<Cotacao>> findByProdutoId(@PathVariable Long produtoId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do produto: " + produtoId);
        Page<Cotacao> list = cotacaoService.findByProdutoId(produtoId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/produto/info/{produtoId}")
    public ResponseEntity<InfoCotacaoDTO> getInfoByProdutoId(@PathVariable Long produtoId) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando informações (min, avg, max) de cotações por ID do produto: " + produtoId);
        InfoCotacaoDTO info = cotacaoService.getInfoByProdutoId(produtoId);
        return ResponseEntity.ok(info);
    }

    // Retornando Objeto ao invés de DTO por solicitação do Front.
    @GetMapping("/status/{statusId}")
    public ResponseEntity<Page<Cotacao>> findByStatusId(@PathVariable Long statusId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do status: " + statusId);
        Page<Cotacao> list = cotacaoService.findByStatusId(statusId, pageable);
        return ResponseEntity.ok(list);
    }
}