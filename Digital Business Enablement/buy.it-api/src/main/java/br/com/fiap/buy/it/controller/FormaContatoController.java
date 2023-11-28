package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.FormaContatoDTO;
import br.com.fiap.buy.it.model.FormaContato;
import br.com.fiap.buy.it.service.FormaContatoService;
import br.com.fiap.buy.it.service.TipoContatoService;
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
@RequestMapping("formasContatos")
@Slf4j
public class FormaContatoController {

    @Autowired
    private FormaContatoService formaContatoService;

    @Autowired
    private TipoContatoService tipoContatoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public Page<FormaContatoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return formaContatoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<FormaContatoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(formaContatoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<FormaContatoDTO> create(@RequestBody @Valid FormaContatoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(formaContatoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<FormaContatoDTO> update(@PathVariable Long id,
            @RequestBody @Valid FormaContatoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(formaContatoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        formaContatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private FormaContatoDTO convertToDto(FormaContato formaContato) {
        FormaContatoDTO dto = new FormaContatoDTO();
        dto.setId(formaContato.getId());
        dto.setIdTipoContato(formaContato.getTipoContato() != null ? formaContato.getTipoContato().getId() : null);
        dto.setValor(formaContato.getValor());
        dto.setIdPessoa(formaContato.getPessoa() != null ? formaContato.getPessoa().getId() : null);
        return dto;
    }

    private FormaContato convertToEntity(FormaContatoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(FormaContato) ID FormaContato não pode ser nulo.");
        }
        if (dto.getIdTipoContato() == null) {
            throw new IllegalArgumentException("(FormaContato) ID TipoContato não pode ser nulo.");
        }
        if (dto.getIdPessoa() == null) {
            throw new IllegalArgumentException("(FormaContato) ID Pessoa não pode ser nulo.");
        }
        FormaContato formaContato = new FormaContato();
        formaContato.setId(dto.getId());
        if (dto.getIdTipoContato() != null)
            formaContato.setTipoContato(tipoContatoService.findById(dto.getIdTipoContato()));
        formaContato.setValor(dto.getValor());
        if (dto.getIdPessoa() != null)
            formaContato.setPessoa(pessoaService.findById(dto.getIdPessoa()));
        return formaContato;
    }
}