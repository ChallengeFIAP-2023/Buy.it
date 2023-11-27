package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.FormaContato;
import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.repository.FormaContatoRepository;
import br.com.fiap.buy.it.repository.PessoaRepository;
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
@RequestMapping("formasContato")
@Slf4j
public class FormaContatoController {

    @Autowired
    private FormaContatoRepository formaContatoRepository;

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public Page<FormaContato> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest
    ) {
        log.info("(FormaContato) - Buscando todos(as)");
        return formaContatoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<FormaContato> findById(@PathVariable Long id) {
        log.info("(FormaContato) - Exibindo por ID: " + id);
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<FormaContato> create(@RequestBody @Valid FormaContato newData) {
        log.info("(FormaContato) - Cadastrando: " + newData);

        TipoContato tipoContato = tipoContatoRepository.findById(newData.getTipoContato().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - TipoContato não encontrado(a) por ID: " + newData.getTipoContato().getId()));
        newData.setTipoContato(tipoContato);

        Pessoa pessoa = pessoaRepository.findById(newData.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - Pessoa não encontrado(a) por ID: " + newData.getPessoa().getId()));
        newData.setPessoa(pessoa);

        FormaContato savedData = formaContatoRepository.save(newData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PutMapping("{id}")
    public ResponseEntity<FormaContato> update(@PathVariable Long id, @RequestBody @Valid FormaContato updatedData) {
        log.info("(FormaContato) - Atualizando por ID: " + id);
        
        getById(id);
        updatedData.setId(id);

        TipoContato tipoContato = tipoContatoRepository.findById(updatedData.getTipoContato().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - TipoContato não encontrado(a) por ID: " + updatedData.getTipoContato().getId()));
        updatedData.setTipoContato(tipoContato);

        Pessoa pessoa = pessoaRepository.findById(updatedData.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - Pessoa não encontrado(a) por ID: " + updatedData.getPessoa().getId()));
        updatedData.setPessoa(pessoa);

        formaContatoRepository.save(updatedData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FormaContato> delete(@PathVariable Long id) {
        log.info("(FormaContato) - Deletando por ID: " + id);
        formaContatoRepository.delete(getById(id));
        return ResponseEntity.noContent().build();
    }

    private FormaContato getById(Long id) {
        return formaContatoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(FormaContato) não encontrado(a) por ID: " + id));
    }
}