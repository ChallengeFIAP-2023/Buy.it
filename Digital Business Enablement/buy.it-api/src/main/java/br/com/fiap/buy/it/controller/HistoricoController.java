package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.HistoricoDTO;
import br.com.fiap.buy.it.model.Historico;
import br.com.fiap.buy.it.service.HistoricoService;
import br.com.fiap.buy.it.service.CotacaoService;
import br.com.fiap.buy.it.service.StatusService;
import br.com.fiap.buy.it.service.UsuarioService;

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
@RequestMapping("historicos")
@Slf4j
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<HistoricoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return historicoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<HistoricoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(historicoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HistoricoDTO> create(@RequestBody @Valid HistoricoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(historicoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<HistoricoDTO> update(@PathVariable Long id, @RequestBody @Valid HistoricoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(historicoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        historicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private HistoricoDTO convertToDto(Historico historico) {
        HistoricoDTO dto = new HistoricoDTO();
        dto.setId(historico.getId());
        dto.setIdCotacao(historico.getCotacao() != null ? historico.getCotacao().getId() : null);
        dto.setIdFornecedor(historico.getFornecedor() != null ? historico.getFornecedor().getId() : null);
        dto.setIdStatus(historico.getStatus() != null ? historico.getStatus().getId() : null);
        dto.setRecusadoPorProduto(historico.getRecusadoPorProduto());
        dto.setRecusadoPorQuantidade(historico.getRecusadoPorQuantidade());
        dto.setRecusadoPorPreco(historico.getRecusadoPorPreco());
        dto.setRecusadoPorPrazo(historico.getRecusadoPorPrazo());
        dto.setDescricao(historico.getDescricao());
        dto.setData(historico.getData());
        dto.setValorOfertado(historico.getValorOfertado());
        return dto;
    }

    private Historico convertToEntity(HistoricoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Historico) ID Historico não pode ser nulo.");
        }
        if (dto.getIdCotacao() == null) {
            throw new IllegalArgumentException("(Historico) ID Cotacao não pode ser nulo.");
        }
        if (dto.getIdFornecedor() == null) {
            throw new IllegalArgumentException("(Historico) ID Fornecedor não pode ser nulo.");
        }
        if (dto.getIdStatus() == null) {
            throw new IllegalArgumentException("(Historico) ID Status não pode ser nulo.");
        }
        Historico historico = new Historico();
        historico.setId(dto.getId());
        if (dto.getIdCotacao() != null)
            historico.setCotacao(cotacaoService.findById(dto.getIdCotacao()));
        if (dto.getIdFornecedor() != null)
            historico.setFornecedor(usuarioService.findById(dto.getIdFornecedor()));
        if (dto.getIdStatus() != null)
            historico.setStatus(statusService.findById(dto.getIdStatus()));
        historico.setRecusadoPorProduto(dto.getRecusadoPorProduto());
        historico.setRecusadoPorQuantidade(dto.getRecusadoPorQuantidade());
        historico.setRecusadoPorPreco(dto.getRecusadoPorPreco());
        historico.setRecusadoPorPrazo(dto.getRecusadoPorPrazo());
        historico.setDescricao(dto.getDescricao());
        historico.setData(dto.getData());
        historico.setValorOfertado(dto.getValorOfertado());
        return historico;
    }
}