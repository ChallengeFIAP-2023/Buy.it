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
        Pessoa pessoa = findEntityById(id);
        return convertToDto(pessoa);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Pessoa) - Pessoa não encontrado(a) por ID: " + id));
    }

    private PessoaDTO convertToDto(Pessoa pessoa) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(pessoa.getId());
        dto.setNome(pessoa.getNome());
        dto.setUrlImagem(pessoa.getUrlImagem());
        return dto;
    }

    private Pessoa convertToEntity(PessoaDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Pessoa pessoa = new Pessoa();
        if (dto.getId() != null)
            pessoa.setId(dto.getId());
        pessoa.setNome(dto.getNome());
        pessoa.setUrlImagem(dto.getUrlImagem());
        return pessoa;
    }
}