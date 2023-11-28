package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Pessoa> listAll(Pageable pageRequest) {
        return pessoaRepository.findAll(pageRequest);
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Pessoa) não encontrado(a) por ID: " + id));
    }

    public Pessoa create(Pessoa newData) {
        return pessoaRepository.save(newData);
    }

    public Pessoa update(Long id, Pessoa updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Pessoa) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        return pessoaRepository.save(updatedData);
    }

    public void delete(Long id) {
        pessoaRepository.delete(findById(id));
    }
}