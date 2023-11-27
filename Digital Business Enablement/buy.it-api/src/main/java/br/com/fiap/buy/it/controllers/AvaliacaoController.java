package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.repository.AvaliacaoRepository;
import br.com.fiap.buy.it.repository.CotacaoRepository;

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
@RequestMapping("avaliacoes")
@Slf4j
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @GetMapping
    public Page<Avaliacao> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Avaliacao) - Buscando todos(as)");
        return avaliacaoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Avaliacao> findById(@PathVariable Long id) {
        log.info("(Avaliacao) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Avaliacao> create(@RequestBody @Valid Avaliacao newData) {
        log.info("(Avaliacao) - Cadastrando: " + newData);

        Cotacao cotacao = cotacaoRepository.findById(newData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Avaliacao) - Cotacao não encontrado(a) por ID: " + newData.getCotacao().getId()));
        newData.setCotacao(cotacao);

        Avaliacao savedData = avaliacaoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Avaliacao> update(@PathVariable Long id, @RequestBody @Valid Avaliacao updatedData) {
        log.info("(Avaliacao) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Cotacao cotacao = cotacaoRepository.findById(updatedData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Avaliacao) - Cotacao não encontrado(a) por ID: " + updatedData.getCotacao().getId()));
        updatedData.setCotacao(cotacao);

        avaliacaoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Avaliacao> delete(@PathVariable Long id) {
        log.info("(Avaliacao) - Deletando por ID: " + id);
        avaliacaoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Avaliacao getById(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Avaliacao) não encontrado(a) por ID: " + id));
    }
}