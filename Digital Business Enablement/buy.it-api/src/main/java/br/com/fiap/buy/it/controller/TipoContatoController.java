package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.TipoContatoDTO;
import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.service.TipoContatoService;

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
@RequestMapping("tiposContatos")
@Slf4j
public class TipoContatoController {

    @Autowired
    private TipoContatoService tipoContatoService;

    @GetMapping
    public Page<TipoContatoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return tipoContatoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoContatoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(tipoContatoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TipoContatoDTO> create(@RequestBody @Valid TipoContatoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(tipoContatoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<TipoContatoDTO> update(@PathVariable Long id,
            @RequestBody @Valid TipoContatoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(tipoContatoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        tipoContatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TipoContatoDTO convertToDto(TipoContato tipoContato) {
        TipoContatoDTO dto = new TipoContatoDTO();
        dto.setId(tipoContato.getId());
        dto.setNome(tipoContato.getNome());
        return dto;
    }

    private TipoContato convertToEntity(TipoContatoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(TipoContato) ID TipoContato não pode ser nulo.");
        }
        TipoContato tipoContato = new TipoContato();
        tipoContato.setId(dto.getId());
        tipoContato.setNome(dto.getNome());
        return tipoContato;
    }
}