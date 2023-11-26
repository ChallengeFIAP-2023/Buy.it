package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.CotacaoRepository;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.repository.StatusRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;
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
public class CotacaoController {

    @Autowired
    private CotacaoRepository cotacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private StatusRepository statusRepository;



    @GetMapping("/cotacoes")
    public ResponseEntity<Page<Cotacao>> getAllCotacoes(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Cotacao> cotacoes = cotacaoRepository.findAll(pageable);
        return ResponseEntity.ok(cotacoes);
    }

    @GetMapping("/cotacao/{id}")
    public ResponseEntity<Cotacao> getCotacaoById(@PathVariable Long id) {
        Cotacao cotacao = cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cotação não encontrada com ID: " + id));
        return ResponseEntity.ok(cotacao);
    }

    @PostMapping("/cotacao")
    public ResponseEntity<Cotacao> createCotacao(@RequestBody @Valid Cotacao novaCotacao) {
        Usuario comprador = usuarioRepository.findById(novaCotacao.getComprador().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com o ID: " + novaCotacao.getComprador().getId()));

        Produto produto = produtoRepository.findById(novaCotacao.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado com o ID: " + novaCotacao.getProduto().getId()));

        Status status = statusRepository.findById(novaCotacao.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Status não encontrado com o ID: " + novaCotacao.getStatus().getId()));

        novaCotacao.setComprador(comprador);
        novaCotacao.setProduto(produto);
        novaCotacao.setStatus(status);

        Cotacao savedCotacao = cotacaoRepository.save(novaCotacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCotacao);
    }


    @PutMapping("/cotacao/{id}")
    public ResponseEntity<Cotacao> updateCotacao(@PathVariable Long id, @RequestBody @Valid Cotacao cotacaoDetails) {
        Cotacao cotacao = cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cotação não encontrada com ID: " + id));

        Usuario comprador = usuarioRepository.findById(cotacaoDetails.getComprador().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com o ID: " + cotacaoDetails.getComprador().getId()));

        Produto produto = produtoRepository.findById(cotacaoDetails.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado com o ID: " + cotacaoDetails.getProduto().getId()));

        Status status = statusRepository.findById(cotacaoDetails.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Status não encontrado com o ID: " + cotacaoDetails.getStatus().getId()));

        cotacao.setComprador(comprador);
        cotacao.setProduto(produto);
        cotacao.setStatus(status);
        cotacao.setDataAbertura(cotacaoDetails.getDataAbertura());
        cotacao.setQuantidadeProduto(cotacaoDetails.getQuantidadeProduto());
        cotacao.setValorProduto(cotacaoDetails.getValorProduto());
        cotacao.setPrioridadeEntrega(cotacaoDetails.getPrioridadeEntrega());
        cotacao.setPrioridadeQualidade(cotacaoDetails.getPrioridadeQualidade());
        cotacao.setPrioridadePreco(cotacaoDetails.getPrioridadePreco());
        cotacao.setPrazo(cotacaoDetails.getPrazo());
        cotacao.setDataFechamento(cotacaoDetails.getDataFechamento());

        final Cotacao updatedCotacao = cotacaoRepository.save(cotacao);
        return ResponseEntity.ok(updatedCotacao);
    }


    @DeleteMapping("/cotacao/{id}")
    public ResponseEntity<HttpStatus> deleteCotacao(@PathVariable Long id) {
        Cotacao cotacao = cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cotação não encontrada com ID: " + id));

        cotacaoRepository.delete(cotacao);
        return ResponseEntity.noContent().build();
    }
}
