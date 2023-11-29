package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.FormaContatoDTO;
import br.com.fiap.buy.it.model.FormaContato;
import br.com.fiap.buy.it.repository.FormaContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class FormaContatoService {

    @Autowired
    private FormaContatoRepository formaContatoRepository;

    @Autowired
    private TipoContatoService tipoContatoService;

    @Autowired
    private PessoaService pessoaService;

    public Page<FormaContatoDTO> listAll(Pageable pageRequest) {
        return formaContatoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public FormaContatoDTO findById(Long id) {
        FormaContato entity = findEntityById(id);
        return convertToDto(entity);
    }

    public FormaContatoDTO create(FormaContatoDTO newData) {
        FormaContato entity = convertToEntity(newData);
        FormaContato savedEntity = formaContatoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public FormaContatoDTO update(Long id, FormaContatoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        FormaContato updatedEntity = convertToEntity(updatedData);    
        FormaContato savedEntity = formaContatoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        FormaContato entity = findEntityById(id);
        formaContatoRepository.delete(entity);
    }

    public FormaContato findEntityById(Long id) {
        return formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - FormaContato não encontrado(a) por ID: " + id));
    }

    private FormaContatoDTO convertToDto(FormaContato entity) {
        FormaContatoDTO dto = new FormaContatoDTO();
        dto.setId(entity.getId());
        dto.setIdTipoContato(entity.getTipoContato() != null ? entity.getTipoContato().getId() : null);
        dto.setValor(entity.getValor());
        dto.setIdPessoa(entity.getPessoa() != null ? entity.getPessoa().getId() : null);
        return dto;
    }

    private FormaContato convertToEntity(FormaContatoDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - FormaContatoDTO não pode ser nulo.");
        }
        FormaContato entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new FormaContato();
        }
        if (dto.getIdTipoContato() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID TipoContato não pode ser nulo.");
        }
        if (dto.getIdPessoa() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Pessoa não pode ser nulo.");
        }
        entity.setTipoContato(tipoContatoService.findEntityById(dto.getIdTipoContato()));
        entity.setValor(dto.getValor());
        entity.setPessoa(pessoaService.findEntityById(dto.getIdPessoa()));
        return entity;
    }
}