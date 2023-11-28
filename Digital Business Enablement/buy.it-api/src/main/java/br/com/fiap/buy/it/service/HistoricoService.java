package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Historico;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.HistoricoRepository;
import br.com.fiap.buy.it.repository.CotacaoRepository;
import br.com.fiap.buy.it.repository.StatusRepository;
import br.com.fiap.buy.it.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Historico> listAll(Pageable pageRequest) {
        return historicoRepository.findAll(pageRequest);
    }

    public Historico findById(Long id) {
        return historicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) não encontrado(a) por ID: " + id));
    }

    public Historico create(Historico newData) {
        Cotacao cotacao = cotacaoRepository.findById(newData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Cotacao não encontrado(a) por ID: "
                                + newData.getCotacao().getId()));
        newData.setCotacao(cotacao);
        Status status = statusRepository.findById(newData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Status não encontrado(a) por ID: "
                                + newData.getStatus().getId()));
        newData.setStatus(status);
        Usuario fornecedor = usuarioRepository.findById(newData.getFornecedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Usuario não encontrado(a) por ID: "
                                + newData.getFornecedor().getId()));
        newData.setFornecedor(fornecedor);
        return historicoRepository.save(newData);
    }

    public Historico update(Long id, Historico updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Historico) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        Cotacao cotacao = cotacaoRepository.findById(updatedData.getCotacao().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Cotacao não encontrado(a) por ID: "
                                + updatedData.getCotacao().getId()));
        updatedData.setCotacao(cotacao);
        Status status = statusRepository.findById(updatedData.getStatus().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Status não encontrado(a) por ID: "
                                + updatedData.getStatus().getId()));
        updatedData.setStatus(status);
        Usuario fornecedor = usuarioRepository.findById(updatedData.getFornecedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Historico) - Usuario não encontrado(a) por ID: "
                                + updatedData.getFornecedor().getId()));
        updatedData.setFornecedor(fornecedor);
        return historicoRepository.save(updatedData);
    }

    public void delete(Long id) {
        historicoRepository.delete(findById(id));
    }
}