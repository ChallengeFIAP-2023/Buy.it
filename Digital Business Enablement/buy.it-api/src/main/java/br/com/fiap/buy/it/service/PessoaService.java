package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.PessoaDTO;
import br.com.fiap.buy.it.model.Pessoa;
import br.com.fiap.buy.it.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<PessoaDTO> listAll(Pageable pageRequest) {
        return pessoaRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public PessoaDTO findById(Long id) {
        Pessoa entity = findEntityById(id);
        return convertToDto(entity);
    }

    public PessoaDTO create(PessoaDTO newData) {
        Pessoa entity = convertToEntity(newData);
        Pessoa savedEntity = pessoaRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public PessoaDTO update(Long id, PessoaDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Pessoa updatedEntity = convertToEntity(updatedData);    
        Pessoa savedEntity = pessoaRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        Pessoa entity = findEntityById(id);
        pessoaRepository.delete(entity);
    }

    public Pessoa findEntityById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Pessoa não encontrado(a) por ID: " + id));
    }

    private PessoaDTO convertToDto(Pessoa entity) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setUrlImagem(entity.getUrlImagem());
        return dto;
    }

    private Pessoa convertToEntity(PessoaDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - PessoaDTO não pode ser nulo.");
        }
        Pessoa entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new Pessoa();
        }
        entity.setNome(dto.getNome());
        entity.setUrlImagem(dto.getUrlImagem());
        return entity;
    }
}