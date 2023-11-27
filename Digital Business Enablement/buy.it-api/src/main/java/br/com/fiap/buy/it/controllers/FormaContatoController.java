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
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class FormaContatoController {

    @Autowired
    private FormaContatoRepository formaContatoRepository;
    @Autowired
    private TipoContatoRepository tipoContatoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/formaContatos")
    public ResponseEntity<Page<FormaContato>> getAllFormaContatos(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FormaContato> formaContatos = formaContatoRepository.findAll(pageable);
        return ResponseEntity.ok(formaContatos);
    }

    @GetMapping("/formaContato/{id}")
    public ResponseEntity<FormaContato> getFormaContatoById(@PathVariable Long id) {
        FormaContato formaContato = formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Forma de contato não encontrada com ID: " + id));
        return ResponseEntity.ok(formaContato);
    }

    @PostMapping("/formaContato")
    public ResponseEntity<FormaContato> createFormaContato(@RequestBody @Valid FormaContato formaContato) {
        log.info("Cadastrando forma de contato: {}", formaContato);

        TipoContato tipoContato = tipoContatoRepository.findById(formaContato.getTipoContato().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tipo de contato não encontrado com o ID: " + formaContato.getTipoContato().getId()));

        Pessoa pessoa = pessoaRepository.findById(formaContato.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pessoa não encontrada com o ID: " + formaContato.getPessoa().getId()));

        formaContato.setTipoContato(tipoContato);
        formaContato.setPessoa(pessoa);

        FormaContato savedFormaContato = formaContatoRepository.save(formaContato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFormaContato);
    }

    @PutMapping("/formaContato/{id}")
    public ResponseEntity<FormaContato> updateFormaContato(@PathVariable Long id, @RequestBody @Valid FormaContato formaContatoDetails) {
        FormaContato formaContato = formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Forma de contato não encontrada com ID: " + id));

        TipoContato tipoContato = tipoContatoRepository.findById(formaContatoDetails.getTipoContato().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tipo de contato não encontrado com o ID: " + formaContatoDetails.getTipoContato().getId()));

        Pessoa pessoa = pessoaRepository.findById(formaContatoDetails.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pessoa não encontrada com o ID: " + formaContatoDetails.getPessoa().getId()));

        formaContato.setTipoContato(tipoContato);
        formaContato.setValor(formaContatoDetails.getValor());
        formaContato.setPessoa(pessoa);

        final FormaContato updatedFormaContato = formaContatoRepository.save(formaContato);
        return ResponseEntity.ok(updatedFormaContato);
    }

    @DeleteMapping("/formaContato/{id}")
    public ResponseEntity<HttpStatus> deleteFormaContato(@PathVariable Long id) {
        FormaContato formaContato = formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Forma de contato não encontrada com ID: " + id));

        formaContatoRepository.delete(formaContato);
        return ResponseEntity.noContent().build();
    }
}