package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.CotacaoRepository;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.repository.StatusRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Page<Cotacao> listAll(Pageable pageRequest) {
        return cotacaoRepository.findAll(pageRequest);
    }

    public Cotacao findById(Long id) {
        return cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "(Cotacao) não encontrado(a) por ID: " + id));
    }

    public Cotacao create(Cotacao newData) {
        
        Usuario comprador = usuarioRepository.findById(newData.getComprador().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Usuario não encontrado(a) por ID: " + newData.getComprador().getId()));
        newData.setComprador(comprador);

        Produto produto = produtoRepository.findById(newData.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Produto não encontrado(a) por ID: " + newData.getProduto().getId()));
        newData.setProduto(produto);

        Status status = statusRepository.findById(newData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Status não encontrado(a) por ID: " + newData.getStatus().getId()));
        newData.setStatus(status);

        return cotacaoRepository.save(newData);
    }

    public Cotacao update(Long id, Cotacao updatedData) {
        
        findById(id);
        updatedData.setId(id);

        Usuario comprador = usuarioRepository.findById(updatedData.getComprador().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Usuario não encontrado(a) por ID: " + updatedData.getComprador().getId()));
        updatedData.setComprador(comprador);

        Produto produto = produtoRepository.findById(updatedData.getProduto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Produto não encontrado(a) por ID: " + updatedData.getProduto().getId()));
        updatedData.setProduto(produto);

        Status status = statusRepository.findById(updatedData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Cotacao) - Status não encontrado(a) por ID: " + updatedData.getStatus().getId()));
        updatedData.setStatus(status);

        return cotacaoRepository.save(updatedData);
    }

    public void delete(Long id) {
        cotacaoRepository.delete(findById(id));
    }
}