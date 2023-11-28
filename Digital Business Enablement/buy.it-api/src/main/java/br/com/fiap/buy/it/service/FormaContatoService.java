package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.FormaContato;
import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.repository.FormaContatoRepository;
import br.com.fiap.buy.it.repository.TipoContatoRepository;
import br.com.fiap.buy.it.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FormaContatoService {

    @Autowired
    private FormaContatoRepository formaContatoRepository;

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<FormaContato> listAll(Pageable pageRequest) {
        return formaContatoRepository.findAll(pageRequest);
    }

    public FormaContato findById(Long id) {
        return formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) não encontrado(a) por ID: " + id));
    }

    public FormaContato create(FormaContato newData) {
        TipoContato tipoContato = tipoContatoRepository.findById(newData.getTipoContato().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - TipoContato não encontrado(a) por ID: " + newData.getTipoContato().getId()));
        newData.setTipoContato(tipoContato);
        Pessoa pessoa = pessoaRepository.findById(newData.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - Pessoa não encontrado(a) por ID: " + newData.getPessoa().getId()));
        newData.setPessoa(pessoa);
        return formaContatoRepository.save(newData);
    }

    public FormaContato update(Long id, FormaContato updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(FormaContato) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        TipoContato tipoContato = tipoContatoRepository.findById(updatedData.getTipoContato().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - TipoContato não encontrado(a) por ID: "
                                + updatedData.getTipoContato().getId()));
        updatedData.setTipoContato(tipoContato);
        Pessoa pessoa = pessoaRepository.findById(updatedData.getPessoa().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(FormaContato) - Pessoa não encontrado(a) por ID: " + updatedData.getPessoa().getId()));
        updatedData.setPessoa(pessoa);
        return formaContatoRepository.save(updatedData);
    }

    public void delete(Long id) {
        formaContatoRepository.delete(findById(id));
    }
}