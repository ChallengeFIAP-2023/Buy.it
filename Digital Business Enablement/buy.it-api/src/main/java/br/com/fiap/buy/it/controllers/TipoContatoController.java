package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.repository.TipoContatoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class TipoContatoController {

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @GetMapping("/tipoContatos")
    public ResponseEntity<Page<TipoContato>> getAllTipoContatos(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TipoContato> tipoContatos = tipoContatoRepository.findAll(pageable);
        return ResponseEntity.ok(tipoContatos);
    }

    @PostMapping("/tipoContato")
    public ResponseEntity<TipoContato> createTipoContato(@RequestBody @Valid TipoContato tipoContato) {
        log.info("Cadastrando tipo de contato: {}", tipoContato);
        TipoContato savedTipoContato = tipoContatoRepository.save(tipoContato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoContato);
    }

    @GetMapping("/tipoContato/{id}")
    public ResponseEntity<TipoContato> getTipoContatoById(@PathVariable Long id) {
        TipoContato tipoContato = tipoContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de contato não encontrado com ID: " + id));
        return ResponseEntity.ok(tipoContato);
    }

    @PutMapping("/tipoContato/{id}")
    public ResponseEntity<TipoContato> updateTipoContato(@PathVariable Long id, @RequestBody @Valid TipoContato tipoContatoDetails) {
        TipoContato tipoContato = tipoContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de contato não encontrado com ID: " + id));

        tipoContato.setNome(tipoContatoDetails.getNome());

        final TipoContato updatedTipoContato = tipoContatoRepository.save(tipoContato);
        return ResponseEntity.ok(updatedTipoContato);
    }

    @DeleteMapping("/tipoContato/{id}")
    public ResponseEntity<HttpStatus> deleteTipoContato(@PathVariable Long id) {
        TipoContato tipoContato = tipoContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo de contato não encontrado com ID: " + id));

        tipoContatoRepository.delete(tipoContato);
        return ResponseEntity.noContent().build();
    }
}