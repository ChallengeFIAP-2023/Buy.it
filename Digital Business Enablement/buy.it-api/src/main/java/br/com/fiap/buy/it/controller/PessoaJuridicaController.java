package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.PessoaJuridicaDTO;
import br.com.fiap.buy.it.model.PessoaJuridica;
import br.com.fiap.buy.it.service.PessoaJuridicaService;

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
@RequestMapping("pessoasJuridicas")
@Slf4j
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public Page<PessoaJuridicaDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return pessoaJuridicaService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaJuridicaDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(pessoaJuridicaService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PessoaJuridicaDTO> create(@RequestBody @Valid PessoaJuridicaDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(pessoaJuridicaService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaJuridicaDTO> update(@PathVariable Long id,
            @RequestBody @Valid PessoaJuridicaDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(pessoaJuridicaService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        pessoaJuridicaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PessoaJuridicaDTO convertToDto(PessoaJuridica pessoaJuridica) {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO();
        dto.setId(pessoaJuridica.getId());
        dto.setNome(pessoaJuridica.getNome());
        dto.setUrlImagem(pessoaJuridica.getUrlImagem());
        dto.setCnpj(pessoaJuridica.getCnpj());
        dto.setIsFornecedor(dto.getIsFornecedor());
        return dto;
    }

    private PessoaJuridica convertToEntity(PessoaJuridicaDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(PessoaJuridica) ID PessoaJuridica não pode ser nulo.");
        }
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(dto.getId());
        pessoaJuridica.setNome(dto.getNome());
        pessoaJuridica.setUrlImagem(dto.getUrlImagem());
        pessoaJuridica.setCnpj(dto.getCnpj());
        pessoaJuridica.setIsFornecedor(dto.getIsFornecedor());
        return pessoaJuridica;
    }
}