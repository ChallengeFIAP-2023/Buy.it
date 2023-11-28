package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
import br.com.fiap.buy.it.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private TagRepository tagRepository;

    public Page<Produto> listAll(Pageable pageRequest) {
        return produtoRepository.findAll(pageRequest);
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Produto) não encontrado(a) por ID: " + id));
    }

    public Produto create(Produto newData) {
        Departamento departamento = departamentoRepository.findById(newData.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Produto) - Departamento não encontrado(a) por ID: " + newData.getDepartamento().getId()));
        newData.setDepartamento(departamento);
        validateTags(newData.getTags());
        return produtoRepository.save(newData);
    }

    public Produto update(Long id, Produto updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Produto) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        Departamento departamento = departamentoRepository.findById(updatedData.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Produto) - Departamento não encontrado(a) por ID: " + updatedData.getDepartamento().getId()));
        updatedData.setDepartamento(departamento);
        validateTags(updatedData.getTags());
        return produtoRepository.save(updatedData);
    }

    public void delete(Long id) {
        produtoRepository.delete(findById(id));
    }

    private void validateTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            tagRepository.findById(tag.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(Produto) - Tag não encontrada por ID: " + tag.getId()));
        }
    }
}