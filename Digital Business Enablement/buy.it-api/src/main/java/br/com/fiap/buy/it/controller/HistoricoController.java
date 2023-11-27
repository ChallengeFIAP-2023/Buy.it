package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.model.Historico;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.CotacaoRepository;
import br.com.fiap.buy.it.repository.HistoricoRepository;
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
@RequestMapping("historicos")
@Slf4j
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public Page<Historico> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(Historico) - Buscando todos(as)");
        return historicoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Historico> findById(@PathVariable Long id) {
        log.info("(Historico) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Historico> create(@RequestBody @Valid Historico newData) {
        log.info("(Historico) - Cadastrando: " + newData);

        Cotacao cotacao = cotacaoRepository.findById(newData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Cotacao não encontrado(a) por ID: " + newData.getCotacao().getId()));
        newData.setCotacao(cotacao);

        Usuario fornecedor = usuarioRepository.findById(newData.getFornecedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Fornecedor não encontrado(a) por ID: " + newData.getFornecedor().getId()));
        newData.setFornecedor(fornecedor);

        Status status = statusRepository.findById(newData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Status não encontrado(a) por ID: " + newData.getStatus().getId()));
        newData.setStatus(status);

        Historico savedData = historicoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Historico> update(@PathVariable Long id, @RequestBody @Valid Historico updatedData) {
        log.info("(Historico) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        Cotacao cotacao = cotacaoRepository.findById(updatedData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Cotacao não encontrado(a) por ID: " + updatedData.getCotacao().getId()));
        updatedData.setCotacao(cotacao);

        Usuario fornecedor = usuarioRepository.findById(updatedData.getFornecedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Fornecedor não encontrado(a) por ID: " + updatedData.getFornecedor().getId()));
        updatedData.setFornecedor(fornecedor);

        Status status = statusRepository.findById(updatedData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Status não encontrado(a) por ID: " + updatedData.getStatus().getId()));
        updatedData.setStatus(status);

        historicoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Historico> delete(@PathVariable Long id) {
        log.info("(Historico) - Deletando por ID: " + id);
        historicoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private Historico getById(Long id) {
        return historicoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Historico) não encontrado(a) por ID: " + id));
    }
}