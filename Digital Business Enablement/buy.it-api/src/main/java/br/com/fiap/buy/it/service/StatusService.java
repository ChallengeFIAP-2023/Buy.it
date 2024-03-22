package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.StatusDTO;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Page<StatusDTO> listAll(Pageable pageRequest) {
        Page<Status> list = statusRepository.findAll(pageRequest);
        return list.map(this::convertToDto);
    }

    public StatusDTO findById(Long id) {
        Status entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public StatusDTO create(StatusDTO newData) {
        Status entity = convertToEntity(newData);
        Status savedEntity = statusRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public StatusDTO update(Long id, StatusDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Status updatedEntity = convertToEntity(updatedData);    
        Status savedEntity = statusRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    
    @Transactional
    public void delete(Long id) {
        Status entity = findEntityById(id);
        statusRepository.delete(entity);
    }

    public Status findEntityById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Status não encontrado(a) por ID: " + id));
    }

    private StatusDTO convertToDto(Status entity) {
        StatusDTO dto = new StatusDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    private Status convertToEntity(StatusDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - StatusDTO não pode ser nulo.");
        }
        Status entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new Status();
        }
        entity.setNome(dto.getNome());
        return entity;
    }    
}