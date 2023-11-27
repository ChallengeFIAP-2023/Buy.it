package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.CotacaoDTO;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.service.CotacaoService;
import br.com.fiap.buy.it.service.ProdutoService;
import br.com.fiap.buy.it.service.StatusService;
import br.com.fiap.buy.it.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private StatusService statusService;

    @GetMapping
    public Page<CotacaoDTO> listAll(
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return cotacaoService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CotacaoDTO> findById(@PathVariable Long id) {
        Cotacao cotacao = cotacaoService.findById(id);
        return ResponseEntity.ok(convertToDto(cotacao));
    }

    @PostMapping
    public ResponseEntity<CotacaoDTO> create(@RequestBody CotacaoDTO newData) {
        Cotacao cotacao = cotacaoService.create(convertToEntity(newData));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(cotacao));
    }

    @PutMapping("{id}")
    public ResponseEntity<CotacaoDTO> update(@PathVariable Long id, @RequestBody CotacaoDTO updatedData) {
        Cotacao cotacao = cotacaoService.update(id, convertToEntity(updatedData));
        return ResponseEntity.ok(convertToDto(cotacao));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
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
        Cotacao cotacao = new Cotacao();
        cotacao.setDataAbertura(dto.getDataAbertura());
        if (dto.getIdComprador() != null) {
            Usuario comprador = usuarioService.findById(dto.getIdComprador());
            cotacao.setComprador(comprador);
        }
        if (dto.getIdProduto() != null) {
            Produto produto = produtoService.findById(dto.getIdProduto());
            cotacao.setProduto(produto);
        }
        cotacao.setQuantidadeProduto(dto.getQuantidadeProduto());
        cotacao.setValorProduto(dto.getValorProduto());
        if (dto.getIdStatus() != null) {
            Status status = statusService.findById(dto.getIdStatus());
            cotacao.setStatus(status);
        }
        cotacao.setPrioridadeEntrega(dto.getPrioridadeEntrega());
        cotacao.setPrioridadeQualidade(dto.getPrioridadeQualidade());
        cotacao.setPrioridadePreco(dto.getPrioridadePreco());
        cotacao.setPrazo(dto.getPrazo());
        cotacao.setDataFechamento(dto.getDataFechamento());
        return cotacao;
    }
}