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

import java.util.Objects;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Departamento) - Departamento não encontrado(a) por ID: " + id));
    }

    private DepartamentoDTO convertToDto(Departamento departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setNome(departamento.getNome());
        dto.setIcone(departamento.getIcone());
        Set<Long> idsTags = departamento.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());
        dto.setIdsTags(idsTags);
        return dto;
    }

    private Departamento convertToEntity(DepartamentoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Departamento) ID Departamento não pode ser nulo.");
        }
        Departamento departamento = new Departamento();
        departamento.setId(dto.getId());
        departamento.setNome(dto.getNome());
        departamento.setIcone(dto.getIcone());
        dto.getIdsTags().stream().forEach(id -> {
            Tag tag = tagService.findEntityById(id);
            departamento.addTag(tag);
        });
        return departamento;
    }
}