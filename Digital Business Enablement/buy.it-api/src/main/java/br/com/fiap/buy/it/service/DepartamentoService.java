package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Tag;
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
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private TagRepository tagRepository;

    public Page<Departamento> listAll(Pageable pageRequest) {
        return departamentoRepository.findAll(pageRequest);
    }

    public Departamento findById(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Departamento) não encontrado(a) por ID: " + id));
    }

    public Departamento create(Departamento newData) {
        validateTags(newData.getTags());
        return departamentoRepository.save(newData);
    }

    public Departamento update(Long id, Departamento updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Departamento) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        validateTags(updatedData.getTags());
        return departamentoRepository.save(updatedData);
    }

    public void delete(Long id) {
        departamentoRepository.delete(findById(id));
    }

    private void validateTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            tagRepository.findById(tag.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "(Departamento) - Tag não encontrada por ID: " + tag.getId()));
        }
    }
}