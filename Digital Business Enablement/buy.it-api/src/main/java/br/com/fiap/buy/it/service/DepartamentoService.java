package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.DepartamentoDTO;
import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.repository.DepartamentoRepository;

import br.com.fiap.buy.it.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private TagService tagService;

    public Page<DepartamentoDTO> listAll(Pageable pageRequest) {
        return departamentoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public DepartamentoDTO findById(Long id) {
        Departamento departamento = findEntityById(id);
        return convertToDto(departamento);
    }

    public DepartamentoDTO create(DepartamentoDTO newData) {
        Departamento entity = convertToEntity(newData);
        Departamento savedEntity = departamentoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public DepartamentoDTO update(Long id, DepartamentoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Departamento updatedEntity = convertToEntity(updatedData);    
        Departamento savedEntity = departamentoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }

    public void delete(Long id) {
        Departamento entity = findEntityById(id);
        departamentoRepository.delete(entity);
    }

    public Departamento findEntityById(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Departamento não encontrado(a) por ID: " + id));
    }

    private DepartamentoDTO convertToDto(Departamento departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setNome(departamento.getNome());
        dto.setIcone(departamento.getIcone());
        if (departamento.getTags() != null) {
            Set<Long> idsTags = departamento.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setIdsTags(idsTags);
        }
        return dto;
    }

    private Departamento convertToEntity(DepartamentoDTO dto) {
        Departamento departamento;
        if (dto.getId() != null) {
            departamento = findEntityById(dto.getId());
            Set<Tag> newTags = new LinkedHashSet<>();
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    newTags.add(tag);
                });
            }
            departamento.setTags(newTags);
        } else {
            departamento = new Departamento();
            departamento.setNome(dto.getNome());
            departamento.setIcone(dto.getIcone());
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    departamento.addTag(tag);
                });
            }
        }
        return departamento;
    }
}