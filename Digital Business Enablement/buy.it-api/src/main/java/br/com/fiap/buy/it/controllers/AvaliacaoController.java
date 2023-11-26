package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.repository.AvaliacaoRepository;
import br.com.fiap.buy.it.repository.CotacaoRepository;
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
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private CotacaoRepository cotacaoRepository;

    @GetMapping("/avaliacoes")
    public ResponseEntity<Page<Avaliacao>> getAllAvaliacoes(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Avaliacao> avaliacoes = avaliacaoRepository.findAll(pageable);
        return ResponseEntity.ok(avaliacoes);
    }


    @GetMapping("/avaliacao/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));
        return ResponseEntity.ok(avaliacao);
    }

    @PostMapping("/avaliacao")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody @Valid Avaliacao novaAvaliacao) {
        Long idCotacao = novaAvaliacao.getCotacao().getId();
        Cotacao cotacao = cotacaoRepository.findById(idCotacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cotacao não encontrada com o ID: " + idCotacao));
        novaAvaliacao.setCotacao(cotacao);
        Avaliacao savedAvaliacao = avaliacaoRepository.save(novaAvaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAvaliacao);
    }

    @PutMapping("/avaliacao/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody @Valid Avaliacao avaliacaoDetails) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));

        Long novaCotacaoId = avaliacaoDetails.getCotacao().getId();
        Cotacao novaCotacao = cotacaoRepository.findById(novaCotacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cotacao não encontrada com o ID: " + novaCotacaoId));
        avaliacao.setCotacao(novaCotacao);

        avaliacao.setData(avaliacaoDetails.getData());
        avaliacao.setNotaEntrega(avaliacaoDetails.getNotaEntrega());
        avaliacao.setNotaQualidade(avaliacaoDetails.getNotaQualidade());
        avaliacao.setNotaPreco(avaliacaoDetails.getNotaPreco());
        avaliacao.setDescricao(avaliacaoDetails.getDescricao());

        final Avaliacao updatedAvaliacao = avaliacaoRepository.save(avaliacao);
        return ResponseEntity.ok(updatedAvaliacao);
    }


    @DeleteMapping("/avaliacao/{id}")
    public ResponseEntity<HttpStatus> deleteAvaliacao(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));

        avaliacaoRepository.delete(avaliacao);
        return ResponseEntity.noContent().build();
    }
}
