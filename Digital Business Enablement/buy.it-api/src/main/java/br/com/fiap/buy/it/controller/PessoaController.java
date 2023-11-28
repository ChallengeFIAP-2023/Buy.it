package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.PessoaDTO;
import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.service.PessoaService;

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

import java.util.Objects;

@RestController
@RequestMapping("pessoas")
@Slf4j
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public Page<PessoaDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return pessoaService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(pessoaService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(pessoaService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody @Valid PessoaDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(pessoaService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PessoaDTO convertToDto(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setUrlImagem(pessoa.getUrlImagem());
        return dto;
    }

    private Pessoa convertToEntity(PessoaDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Pessoa) ID Pessoa não pode ser nulo.");
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setNome(dto.getNome());
        pessoa.setUrlImagem(dto.getUrlImagem());
        return pessoa;
    }
}