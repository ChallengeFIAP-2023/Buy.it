package br.com.fiap.buy.it.controllers;

import br.com.fiap.buy.it.model.*;
import br.com.fiap.buy.it.repository.CotacaoRepository;
import br.com.fiap.buy.it.repository.HistoricoRepository;
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

@RestController
@Slf4j
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CotacaoRepository cotacaoRepository;
    @Autowired
    private StatusRepository statusRepository;

    @GetMapping("/historicos")
    public ResponseEntity<Page<Historico>> getAllHistoricos(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Historico> historicos = historicoRepository.findAll(pageable);
        return ResponseEntity.ok(historicos);
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<Historico> getHistoricoById(@PathVariable Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Histórico não encontrado"));
        return ResponseEntity.ok(historico);
    }

    @PostMapping("/historico")
    public ResponseEntity<Historico> createHistorico(@RequestBody @Valid Historico historico) {
        Cotacao cotacao = cotacaoRepository.findById(historico.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cotação não encontrada com o ID: " + historico.getCotacao().getId()));

        Usuario fornecedor = usuarioRepository.findById(historico.getFornecedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fornecedor não encontrado com o ID: " + historico.getFornecedor().getId()));

        Status status = statusRepository.findById(historico.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Status não encontrado com o ID: " + historico.getStatus().getId()));

        historico.setCotacao(cotacao);
        historico.setFornecedor(fornecedor);
        historico.setStatus(status);

        Historico savedHistorico = historicoRepository.save(historico);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHistorico);
    }

    @PutMapping("/historico/{id}")
    public ResponseEntity<Historico> updateHistorico(@PathVariable Long id, @RequestBody @Valid Historico historicoDetails) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Histórico não encontrado"));

        Cotacao cotacao = cotacaoRepository.findById(historicoDetails.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cotação não encontrada com o ID: " + historicoDetails.getCotacao().getId()));

        Usuario fornecedor = usuarioRepository.findById(historicoDetails.getFornecedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fornecedor não encontrado com o ID: " + historicoDetails.getFornecedor().getId()));

        Status status = statusRepository.findById(historicoDetails.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Status não encontrado com o ID: " + historicoDetails.getStatus().getId()));

        historico.setCotacao(cotacao);
        historico.setFornecedor(fornecedor);
        historico.setStatus(status);
        historico.setRecusadoPorProduto(historicoDetails.getRecusadoPorProduto());
        historico.setRecusadoPorQuantidade(historicoDetails.getRecusadoPorQuantidade());
        historico.setRecusadoPorPreco(historicoDetails.getRecusadoPorPreco());
        historico.setRecusadoPorPrazo(historicoDetails.getRecusadoPorPrazo());
        historico.setDescricao(historicoDetails.getDescricao());
        historico.setData(historicoDetails.getData());
        historico.setValorOfertado(historicoDetails.getValorOfertado());

        final Historico updatedHistorico = historicoRepository.save(historico);
        return ResponseEntity.ok(updatedHistorico);
    }

    @DeleteMapping("/historico/{id}")
    public ResponseEntity<HttpStatus> deleteHistorico(@PathVariable Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Histórico não encontrado"));

        historicoRepository.delete(historico);
        return ResponseEntity.noContent().build();
    }
}