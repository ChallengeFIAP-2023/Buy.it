package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.ContatoDTO;
import br.com.fiap.buy.it.model.Contato;
import br.com.fiap.buy.it.repository.ContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Page<ContatoDTO> listAll(Pageable pageRequest) {
        Page<Contato> list = contatoRepository.findAll(pageRequest);
        return list.map(this::convertToDto);
    }

    public ContatoDTO findById(Long id) {
        Contato entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public ContatoDTO create(ContatoDTO newData) {
        Contato entity = convertToEntity(newData);
        Contato savedEntity = contatoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public ContatoDTO update(Long id, ContatoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Contato updatedEntity = convertToEntity(updatedData);    
        Contato savedEntity = contatoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    
    @Transactional
    public void delete(Long id) {
        Contato entity = findEntityById(id);
        contatoRepository.delete(entity);
    }

    public Contato findEntityById(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Contato não encontrado(a) por ID: " + id));
    }

    public Page<ContatoDTO> findByUsuarioId(Long userId, Pageable pageable) {
        if (userId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID do Usuário não pode ser nulo.");
        }
        Page<Contato> list = contatoRepository.findByUsuarioId(userId, pageable);
        return list.map(this::convertToDto);
    }

    private ContatoDTO convertToDto(Contato entity) {
        ContatoDTO dto = new ContatoDTO();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setValor(entity.getValor());
        dto.setIdUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        return dto;
    }

    private Contato convertToEntity(ContatoDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ContatoDTO não pode ser nulo.");
        }
        Contato entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new Contato();
        }
        if (dto.getIdUsuario() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Usuário não pode ser nulo.");
        }
        entity.setTipo(dto.getTipo());
        entity.setValor(dto.getValor());
        entity.setUsuario(usuarioService.findEntityById(dto.getIdUsuario()));
        return entity;
    }
}