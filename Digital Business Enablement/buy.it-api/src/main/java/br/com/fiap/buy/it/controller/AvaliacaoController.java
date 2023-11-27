package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.AvaliacaoDTO;
import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.service.AvaliacaoService;
import br.com.fiap.buy.it.service.CotacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping
    public Page<AvaliacaoDTO> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return avaliacaoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);
        return ResponseEntity.ok(convertToDto(avaliacao));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> create(@RequestBody AvaliacaoDTO newData) {
        Avaliacao avaliacao = avaliacaoService.create(convertToEntity(newData));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(avaliacao));
    }

    @PutMapping("{id}")
    public ResponseEntity<AvaliacaoDTO> update(@PathVariable Long id, @RequestBody AvaliacaoDTO updatedData) {
        Avaliacao avaliacao = avaliacaoService.update(id, convertToEntity(updatedData));
        return ResponseEntity.ok(convertToDto(avaliacao));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
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
        
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setId(dto.getId());
        if (dto.getIdCotacao() != null) {
            avaliacao.setCotacao(cotacaoService.findById(dto.getIdCotacao()));
        }

        avaliacao.setData(dto.getData());
        avaliacao.setNotaEntrega(dto.getNotaEntrega());
        avaliacao.setNotaQualidade(dto.getNotaQualidade());
        avaliacao.setNotaPreco(dto.getNotaPreco());
        avaliacao.setDescricao(dto.getDescricao());
        
        return avaliacao;
    }
}