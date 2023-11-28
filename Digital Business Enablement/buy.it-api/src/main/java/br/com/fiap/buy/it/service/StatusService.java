package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.StatusDTO;
import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Page<StatusDTO> listAll(Pageable pageRequest) {
        return statusRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public StatusDTO findById(Long id) {
        Status status = findEntityById(id);
        return convertToDto(status);
    }

    public StatusDTO create(StatusDTO newData) {
        Status entity = convertToEntity(newData);
        Status savedEntity = statusRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public StatusDTO update(Long id, StatusDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Status updatedEntity = convertToEntity(updatedData);    
        Status savedEntity = statusRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        Status entity = findEntityById(id);
        statusRepository.delete(entity);
    }

    public Status findEntityById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Status) - Status não encontrado(a) por ID: " + id));
    }

    private StatusDTO convertToDto(Status status) {
        StatusDTO dto = new StatusDTO();
        dto.setId(status.getId());
        dto.setNome(status.getNome());
        return dto;
    }

    private Status convertToEntity(StatusDTO dto) {
        if (dto == null) {
            return null;
        }
        Status status = new Status();
        if (dto.getId() != null)
            status.setId(dto.getId());
        status.setNome(dto.getNome());
        return status;
    }
}