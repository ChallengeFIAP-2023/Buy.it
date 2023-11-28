package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.repository.AvaliacaoRepository;
import br.com.fiap.buy.it.repository.CotacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    public Page<Avaliacao> listAll(Pageable pageRequest) {
        return avaliacaoRepository.findAll(pageRequest);
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Avaliacao) não encontrado(a) por ID: " + id));
    }

    public Avaliacao create(Avaliacao newData) {
        Cotacao cotacao = cotacaoRepository.findById(newData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Avaliacao) - Cotacao não encontrado(a) por ID: " + newData.getCotacao().getId()));
        newData.setCotacao(cotacao);
        return avaliacaoRepository.save(newData);
    }

    public Avaliacao update(Long id, Avaliacao updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Avaliacao) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        Cotacao cotacao = cotacaoRepository.findById(updatedData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Avaliacao) - Cotacao não encontrado(a) por ID: " + updatedData.getCotacao().getId()));
        updatedData.setCotacao(cotacao);
        return avaliacaoRepository.save(updatedData);
    }

    public void delete(Long id) {
        avaliacaoRepository.delete(findById(id));
    }

    public Optional<Avaliacao> findByIdCotacao(Long id) {
        return avaliacaoRepository.findByIdCotacao(id);
    }
}