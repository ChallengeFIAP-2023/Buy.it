package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.repository.StatusRepository;
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

import java.util.List;

@RestController
@Slf4j
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @PostMapping("/status")
    public ResponseEntity<Status> createStatus(@RequestBody @Valid Status status) {
        Status savedStatus = statusRepository.save(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStatus);
    }

    @GetMapping("/status")
    public ResponseEntity<Page<Status>> getAllStatuses(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Status> statuses = statusRepository.findAll(pageable);
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado"));
        return ResponseEntity.ok(status);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Long id, @RequestBody @Valid Status statusDetails) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado"));

        status.setNome(statusDetails.getNome());

        final Status updatedStatus = statusRepository.save(status);
        return ResponseEntity.ok(updatedStatus);
    }

    @DeleteMapping("/status/{id}")
    public ResponseEntity<HttpStatus> deleteStatus(@PathVariable Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status não encontrado"));

        statusRepository.delete(status);
        return ResponseEntity.noContent().build();
    }
}