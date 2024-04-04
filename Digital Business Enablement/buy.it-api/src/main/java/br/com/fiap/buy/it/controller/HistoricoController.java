package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.HistoricoDTO;
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
    public ResponseEntity<Page<HistoricoDTO>> listAll(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return ResponseEntity.ok(historicoService.listAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<HistoricoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(historicoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HistoricoDTO> create(@RequestBody @Valid HistoricoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoService.create(newData));
    }

    @PutMapping("{id}")
    public ResponseEntity<HistoricoDTO> update(@PathVariable Long id, @RequestBody @Valid HistoricoDTO updatedData) {
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
    public ResponseEntity<Page<HistoricoDTO>> findByCotacaoId(@PathVariable Long cotacaoId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID da cotacao: " + cotacaoId);
        Page<HistoricoDTO> list = historicoService.findByCotacaoId(cotacaoId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/usuario/comprador/{compradorId}")
    public ResponseEntity<Page<HistoricoDTO>> findByCompradorId(@PathVariable Long compradorId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do comprador: " + compradorId);
        Page<HistoricoDTO> list = historicoService.findByCompradorId(compradorId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/usuario/fornecedor/{fornecedorId}")
    public ResponseEntity<Page<HistoricoDTO>> findByFornecedorId(@PathVariable Long fornecedorId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do fornecedor: " + fornecedorId);
        Page<HistoricoDTO> list = historicoService.findByFornecedorId(fornecedorId, pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<Page<HistoricoDTO>> findByStatusId(@PathVariable Long statusId, @PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando por ID do status: " + statusId);
        Page<HistoricoDTO> list = historicoService.findByStatusId(statusId, pageable);
        return ResponseEntity.ok(list);
    }
}