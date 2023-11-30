package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.PessoaJuridicaDTO;
import br.com.fiap.buy.it.model.PessoaJuridica;
import br.com.fiap.buy.it.repository.PessoaJuridicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public Page<PessoaJuridica> listAll(Pageable pageRequest) {
        return pessoaJuridicaRepository.findAll(pageRequest);
    }

    public PessoaJuridica findById(Long id) {
        PessoaJuridica entity = findEntityById(id);
        return entity;
    }

    public PessoaJuridica create(PessoaJuridicaDTO newData) {
        PessoaJuridica entity = convertToEntity(newData);
        PessoaJuridica savedEntity = pessoaJuridicaRepository.save(entity);
        return savedEntity;
    }

    public PessoaJuridica update(Long id, PessoaJuridicaDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        PessoaJuridica updatedEntity = convertToEntity(updatedData);    
        PessoaJuridica savedEntity = pessoaJuridicaRepository.save(updatedEntity);
        return savedEntity;
    }
    
    public void delete(Long id) {
        PessoaJuridica entity = findEntityById(id);
        pessoaJuridicaRepository.delete(entity);
    }

    public PessoaJuridica findEntityById(Long id) {
        return pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - PessoaJuridica não encontrado(a) por ID: " + id));
    }

    private PessoaJuridicaDTO convertToDto(PessoaJuridica entity) {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setUrlImagem(entity.getUrlImagem());
        dto.setCnpj(entity.getCnpj());
        dto.setIsFornecedor(dto.getIsFornecedor());
        return dto;
    }

    private PessoaJuridica convertToEntity(PessoaJuridicaDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - PessoaJuridicaDTO não pode ser nulo.");
        }
        PessoaJuridica entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new PessoaJuridica();
        }
        entity.setNome(dto.getNome());
        entity.setUrlImagem(dto.getUrlImagem());
        entity.setCnpj(dto.getCnpj());
        entity.setIsFornecedor(dto.getIsFornecedor());
    
        return entity;
    }
}