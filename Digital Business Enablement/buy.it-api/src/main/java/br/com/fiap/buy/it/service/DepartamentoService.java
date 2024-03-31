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
import org.springframework.transaction.annotation.Transactional;
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
        Page<Departamento> list = departamentoRepository.findAll(pageRequest);
        return list.map(this::convertToDto);
    }

    public DepartamentoDTO findById(Long id) {
        Departamento entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public DepartamentoDTO create(DepartamentoDTO newData) {
        Departamento entity = convertToEntity(newData);
        Departamento savedEntity = departamentoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public DepartamentoDTO update(Long id, DepartamentoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Departamento updatedEntity = convertToEntity(updatedData);    
        Departamento savedEntity = departamentoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public void delete(Long id) {
        Departamento entity = findEntityById(id);
        if (entity.getTags() != null) {
            for (Tag tag : entity.getTags()) {
                tag.removeDepartamento(entity);
            }
        }
        departamentoRepository.delete(entity);
    }

    public Departamento findEntityById(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Departamento não encontrado(a) por ID: " + id));
    }

    public Page<DepartamentoDTO> findByTagId(Long tagId, Pageable pageable) {
        if (tagId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID da Tag não pode ser nulo.");
        }
        Page<Departamento> list = departamentoRepository.findByTagId(tagId, pageable);
        return list.map(this::convertToDto);
    }

    private DepartamentoDTO convertToDto(Departamento entity) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setIcone(entity.getIcone());
        if (entity.getTags() != null) {
            Set<Long> idsTags = entity.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setIdsTags(idsTags);
        }
        return dto;
    }

    private Departamento convertToEntity(DepartamentoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - DepartamentoDTO não pode ser nulo.");
        }
        Departamento entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
            entity.setNome(dto.getNome());
            entity.setIcone(dto.getIcone());
            Set<Tag> newTags = new LinkedHashSet<>();
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    newTags.add(tag);
                });
            }
            entity.setTags(newTags);
        } else {
            entity = new Departamento();
            entity.setNome(dto.getNome());
            entity.setIcone(dto.getIcone());
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    entity.addTag(tag);
                });
            }
        }
        return entity;
    }
}