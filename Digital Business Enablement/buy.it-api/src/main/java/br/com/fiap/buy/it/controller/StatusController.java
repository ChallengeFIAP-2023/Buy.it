package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.StatusDTO;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.service.StatusService;

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
@RequestMapping("status")
@Slf4j
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public Page<StatusDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return statusService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(statusService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<StatusDTO> create(@RequestBody @Valid StatusDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(statusService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<StatusDTO> update(@PathVariable Long id, @RequestBody @Valid StatusDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(statusService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        statusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private StatusDTO convertToDto(Status status) {
        StatusDTO dto = new StatusDTO();
        dto.setId(status.getId());
        dto.setNome(status.getNome());
        return dto;
    }

    private Status convertToEntity(StatusDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Status) ID Status não pode ser nulo.");
        }
        Status status = new Status();
        status.setId(dto.getId());
        status.setNome(dto.getNome());
        return status;
    }
}