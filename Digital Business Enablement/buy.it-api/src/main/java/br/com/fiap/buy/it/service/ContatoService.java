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
    private ContatoRepository formaContatoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Page<Contato> listAll(Pageable pageRequest) {
        return formaContatoRepository.findAll(pageRequest);
    }

    public Contato findById(Long id) {
        Contato entity = findEntityById(id);
        return entity;
    }

    @Transactional
    public Contato create(ContatoDTO newData) {
        Contato entity = convertToEntity(newData);
        Contato savedEntity = formaContatoRepository.save(entity);
        return savedEntity;
    }

    @Transactional
    public Contato update(Long id, ContatoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Contato updatedEntity = convertToEntity(updatedData);    
        Contato savedEntity = formaContatoRepository.save(updatedEntity);
        return savedEntity;
    }
    
    @Transactional
    public void delete(Long id) {
        Contato entity = findEntityById(id);
        formaContatoRepository.delete(entity);
    }

    public Contato findEntityById(Long id) {
        return formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Contato não encontrado(a) por ID: " + id));
    }

    // private ContatoDTOconvertToDto(Contato entity) {
    //     ContatoDTOdto = new ContatoDTO();
    //     dto.setId(entity.getId());
    //     dto.setIdTipoContato(entity.getTipoContato() != null ? entity.getTipoContato().getId() : null);
    //     dto.setValor(entity.getValor());
    //     dto.setIdPessoa(entity.getPessoa() != null ? entity.getPessoa().getId() : null);
    //     return dto;
    // }

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