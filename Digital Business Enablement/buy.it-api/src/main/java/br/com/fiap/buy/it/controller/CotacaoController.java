package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.CotacaoDTO;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.service.CotacaoService;
import br.com.fiap.buy.it.service.ProdutoService;
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
@RequestMapping("cotacoes")
@Slf4j
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<CotacaoDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return cotacaoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CotacaoDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(cotacaoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CotacaoDTO> create(@RequestBody @Valid CotacaoDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(cotacaoService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<CotacaoDTO> update(@PathVariable Long id, @RequestBody @Valid CotacaoDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(cotacaoService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        cotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CotacaoDTO convertToDto(Cotacao cotacao) {
        CotacaoDTO dto = new CotacaoDTO();
        dto.setId(cotacao.getId());
        dto.setDataAbertura(cotacao.getDataAbertura());
        dto.setIdComprador(cotacao.getComprador() != null ? cotacao.getComprador().getId() : null);
        dto.setIdProduto(cotacao.getProduto() != null ? cotacao.getProduto().getId() : null);
        dto.setQuantidadeProduto(cotacao.getQuantidadeProduto());
        dto.setValorProduto(cotacao.getValorProduto());
        dto.setIdStatus(cotacao.getStatus() != null ? cotacao.getStatus().getId() : null);
        dto.setPrioridadeEntrega(cotacao.getPrioridadeEntrega());
        dto.setPrioridadeQualidade(cotacao.getPrioridadeQualidade());
        dto.setPrioridadePreco(cotacao.getPrioridadePreco());
        dto.setPrazo(cotacao.getPrazo());
        dto.setDataFechamento(cotacao.getDataFechamento());
        return dto;
    }

    private Cotacao convertToEntity(CotacaoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Cotacao não pode ser nulo.");
        }
        if (dto.getIdComprador() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Comprador não pode ser nulo.");
        }
        if (dto.getIdProduto() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Produto não pode ser nulo.");
        }
        if (dto.getIdStatus() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Status não pode ser nulo.");
        }
        Cotacao cotacao = new Cotacao();
        cotacao.setId(dto.getId());
        cotacao.setDataAbertura(dto.getDataAbertura());
        if (dto.getIdComprador() != null)
            cotacao.setComprador(usuarioService.findById(dto.getIdComprador()));
        if (dto.getIdProduto() != null)
            cotacao.setProduto(produtoService.findById(dto.getIdProduto()));
        cotacao.setQuantidadeProduto(dto.getQuantidadeProduto());
        cotacao.setValorProduto(dto.getValorProduto());
        if (dto.getIdStatus() != null)
            cotacao.setStatus(statusService.findById(dto.getIdStatus()));
        cotacao.setPrioridadeEntrega(dto.getPrioridadeEntrega());
        cotacao.setPrioridadeQualidade(dto.getPrioridadeQualidade());
        cotacao.setPrioridadePreco(dto.getPrioridadePreco());
        cotacao.setPrazo(dto.getPrazo());
        cotacao.setDataFechamento(dto.getDataFechamento());
        return cotacao;
    }
}