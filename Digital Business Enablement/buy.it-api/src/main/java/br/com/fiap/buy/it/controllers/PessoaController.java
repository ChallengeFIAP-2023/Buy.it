package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.repository.PessoaRepository;
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
@Slf4j
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/pessoas")
    public ResponseEntity<Page<Pessoa>> getAllPessoas(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);
        return ResponseEntity.ok(pessoas);
    }
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id));
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping("/pessoa")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody @Valid Pessoa pessoa) {
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @PutMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody @Valid Pessoa pessoaDetails) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id));

        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setUrlImagem(pessoaDetails.getUrlImagem());

        final Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<HttpStatus> deletePessoa(@PathVariable Long id) {
        ResponseEntity<Pessoa> responseEntity = getPessoaById(id);
        Pessoa pessoa = responseEntity.getBody();

        if (pessoa != null) {
            pessoaRepository.delete(pessoa);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id);
        }
    }

}

