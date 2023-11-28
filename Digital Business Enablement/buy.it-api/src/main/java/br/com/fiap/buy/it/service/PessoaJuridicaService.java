package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.PessoaJuridica;
import br.com.fiap.buy.it.repository.PessoaJuridicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public Page<PessoaJuridica> listAll(Pageable pageRequest) {
        return pessoaJuridicaRepository.findAll(pageRequest);
    }

    public PessoaJuridica findById(Long id) {
        return pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(PessoaJuridica) não encontrado(a) por ID: " + id));
    }

    public PessoaJuridica create(PessoaJuridica newData) {
        return pessoaJuridicaRepository.save(newData);
    }

    public PessoaJuridica update(Long id, PessoaJuridica updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(PessoaJuridica) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        return pessoaJuridicaRepository.save(updatedData);
    }

    public void delete(Long id) {
        pessoaJuridicaRepository.delete(findById(id));
    }
}