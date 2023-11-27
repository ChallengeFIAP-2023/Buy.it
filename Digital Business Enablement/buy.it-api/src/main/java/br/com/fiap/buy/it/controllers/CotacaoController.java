package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.CotacaoRepository;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.repository.StatusRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;

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
@RequestMapping("cotacoes")
@Slf4j
public class CotacaoController {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public Page<Cotacao> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Cotacao) - Buscando todos(as)");
        return cotacaoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cotacao> findById(@PathVariable Long id) {
        log.info("(Cotacao) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Cotacao> create(@RequestBody @Valid Cotacao newData) {
        log.info("(Cotacao) - Cadastrando: " + newData);

        Usuario comprador = usuarioRepository.findById(newData.getComprador().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Usuario não encontrado(a) por ID: " + newData.getComprador().getId()));
        newData.setComprador(comprador);

        Produto produto = produtoRepository.findById(newData.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Produto não encontrado(a) por ID: " + newData.getProduto().getId()));
        newData.setProduto(produto);

        Status status = statusRepository.findById(newData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Status não encontrado(a) por ID: " + newData.getStatus().getId()));
        newData.setStatus(status);

        Cotacao savedData = cotacaoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cotacao> update(@PathVariable Long id, @RequestBody @Valid Cotacao updatedData) {
        log.info("(Cotacao) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Usuario comprador = usuarioRepository.findById(updatedData.getComprador().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Usuario não encontrado(a) por ID: " + updatedData.getComprador().getId()));
        updatedData.setComprador(comprador);

        Produto produto = produtoRepository.findById(updatedData.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Produto não encontrado(a) por ID: " + updatedData.getProduto().getId()));
        updatedData.setProduto(produto);

        Status status = statusRepository.findById(updatedData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Status não encontrado(a) por ID: " + updatedData.getStatus().getId()));
        updatedData.setStatus(status);

        cotacaoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cotacao> delete(@PathVariable Long id) {
        log.info("(Cotacao) - Deletando por ID: " + id);
        cotacaoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Cotacao getById(Long id) {
        return cotacaoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Cotacao) não encontrado(a) por ID: " + id));
    }
}