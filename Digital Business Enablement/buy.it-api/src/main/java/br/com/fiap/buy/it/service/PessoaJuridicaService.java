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

    public Page<PessoaJuridicaDTO> listAll(Pageable pageRequest) {
        return pessoaJuridicaRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public PessoaJuridicaDTO findById(Long id) {
        PessoaJuridica pessoaJuridica = findEntityById(id);
        return convertToDto(pessoaJuridica);
    }

    public PessoaJuridicaDTO create(PessoaJuridicaDTO newData) {
        PessoaJuridica entity = convertToEntity(newData);
        PessoaJuridica savedEntity = pessoaJuridicaRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public PessoaJuridicaDTO update(Long id, PessoaJuridicaDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        PessoaJuridica updatedEntity = convertToEntity(updatedData);    
        PessoaJuridica savedEntity = pessoaJuridicaRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        PessoaJuridica entity = findEntityById(id);
        pessoaJuridicaRepository.delete(entity);
    }

    public PessoaJuridica findEntityById(Long id) {
        return pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(PessoaJuridica) - PessoaJuridica não encontrado(a) por ID: " + id));
    }

    private PessoaJuridicaDTO convertToDto(PessoaJuridica pessoaJuridica) {
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO();
        dto.setId(pessoaJuridica.getId());
        dto.setNome(pessoaJuridica.getNome());
        dto.setUrlImagem(pessoaJuridica.getUrlImagem());
        dto.setCnpj(pessoaJuridica.getCnpj());
        dto.setIsFornecedor(dto.getIsFornecedor());
        return dto;
    }

    private PessoaJuridica convertToEntity(PessoaJuridicaDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(PessoaJuridica) ID PessoaJuridica não pode ser nulo.");
        }
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(dto.getId());
        pessoaJuridica.setNome(dto.getNome());
        pessoaJuridica.setUrlImagem(dto.getUrlImagem());
        pessoaJuridica.setCnpj(dto.getCnpj());
        pessoaJuridica.setIsFornecedor(dto.getIsFornecedor());
        return pessoaJuridica;
    }
}