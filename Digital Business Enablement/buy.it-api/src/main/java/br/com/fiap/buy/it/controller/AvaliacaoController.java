package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.AvaliacaoDTO;
import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.service.AvaliacaoService;
import br.com.fiap.buy.it.service.CotacaoService;

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
@RequestMapping("avaliacoes")
@Slf4j
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping
    public Page<AvaliacaoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return avaliacaoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(avaliacaoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> create(@RequestBody @Valid AvaliacaoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(avaliacaoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<AvaliacaoDTO> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(avaliacaoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private AvaliacaoDTO convertToDto(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setIdCotacao(avaliacao.getCotacao() != null ? avaliacao.getCotacao().getId() : null);
        dto.setData(avaliacao.getData());
        dto.setNotaEntrega(avaliacao.getNotaEntrega());
        dto.setNotaQualidade(avaliacao.getNotaQualidade());
        dto.setNotaPreco(avaliacao.getNotaPreco());
        dto.setDescricao(avaliacao.getDescricao());
        return dto;
    }

    private Avaliacao convertToEntity(AvaliacaoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Avaliacao) ID Avaliacao não pode ser nulo.");
        }
        if (dto.getIdCotacao() == null) {
            throw new IllegalArgumentException("(Avaliacao) ID Cotacao não pode ser nulo.");
        }
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(dto.getId());
        if (dto.getIdCotacao() != null)
            avaliacao.setCotacao(cotacaoService.findById(dto.getIdCotacao()));
        avaliacao.setData(dto.getData());
        avaliacao.setNotaEntrega(dto.getNotaEntrega());
        avaliacao.setNotaQualidade(dto.getNotaQualidade());
        avaliacao.setNotaPreco(dto.getNotaPreco());
        avaliacao.setDescricao(dto.getDescricao());
        return avaliacao;
    }
}