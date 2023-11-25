package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.PessoaJuridica;
import br.com.fiap.buy.it.repository.PessoaJuridicaRepository;
import jakarta.validation.Valid;
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
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping("/pessoasJuridicas")
    public ResponseEntity<Page<PessoaJuridica>> getAllPessoasJuridicas(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<PessoaJuridica> pessoasJuridicas = pessoaJuridicaRepository.findAll(pageable);
        return ResponseEntity.ok(pessoasJuridicas);
    }

    @PostMapping("/pessoaJuridica")
    public ResponseEntity<PessoaJuridica> createPessoaJuridica(@RequestBody @Valid PessoaJuridica pessoaJuridica) {
        PessoaJuridica savedPessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoaJuridica);
    }

    @GetMapping("/pessoaJuridica/{id}")
    public ResponseEntity<PessoaJuridica> getPessoaJuridicaById(@PathVariable Long id) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa Jurídica não encontrada com ID: " + id));
        return ResponseEntity.ok(pessoaJuridica);
    }

    @PutMapping("/pessoaJuridica/{id}")
    public ResponseEntity<PessoaJuridica> updatePessoaJuridica(@PathVariable Long id, @RequestBody @Valid PessoaJuridica pessoaJuridicaDetails) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa Jurídica não encontrada com ID: " + id));

        pessoaJuridica.setIsFornecedor(pessoaJuridicaDetails.getIsFornecedor());
        pessoaJuridica.setCnpj(pessoaJuridicaDetails.getCnpj());

        final PessoaJuridica updatedPessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        return ResponseEntity.ok(updatedPessoaJuridica);
    }

    @DeleteMapping("/pessoaJuridica/{id}")
    public ResponseEntity<HttpStatus> deletePessoaJuridica(@PathVariable Long id) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa Jurídica não encontrada com ID: " + id));

        pessoaJuridicaRepository.delete(pessoaJuridica);
        return ResponseEntity.noContent().build();
    }
}

