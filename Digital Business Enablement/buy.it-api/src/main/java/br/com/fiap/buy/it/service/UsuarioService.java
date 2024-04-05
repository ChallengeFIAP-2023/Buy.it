package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.UsuarioDTO;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.UsuarioRepository;
import br.com.fiap.buy.it.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Page<UsuarioDTO> listAll(Pageable pageRequest) {
        Page<Usuario> list = usuarioRepository.findAll(pageRequest);
        return list.map(this::convertToDto);
    }

    public UsuarioDTO findById(Long id) {
        Usuario entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public UsuarioDTO create(UsuarioDTO newData) {
        Usuario entity = convertToEntity(newData);
        Usuario savedEntity = usuarioRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Usuario updatedEntity = convertToEntity(updatedData);    
        Usuario savedEntity = usuarioRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public void delete(Long id) {
        Usuario entity = findEntityById(id);
        if (entity.getTags() != null) {
            for (Tag tag : entity.getTags()) {
                tag.removeUsuario(entity);
            }
        }
        usuarioRepository.delete(entity);
    }

    public Usuario findEntityById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Usuario não encontrado(a) por ID: " + id));
    }

    public UsuarioDTO findByEmail(String email) {
        Usuario entity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Usuario não encontrado(a) por email: " + email));
        return convertToDto(entity);
    }

    public Page<UsuarioDTO> findByTagId(Long tagId, Pageable pageable) {
        if (tagId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID da Tag não pode ser nulo.");
        }
        Page<Usuario> list = usuarioRepository.findByTagId(tagId, pageable);
        return list.map(this::convertToDto);
    }

    private UsuarioDTO convertToDto(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setSenha(null);
        dto.setUrlImagem(entity.getUrlImagem());
        dto.setCnpj(entity.getCnpj());
        dto.setIsFornecedor(entity.getIsFornecedor());
        if (entity.getTags() != null) {
            Set<Long> idsTags = entity.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setIdsTags(idsTags);
        }
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - UsuarioDTO não pode ser nulo.");
        }
        Usuario entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setSenha(passwordEncoder.encode(dto.getSenha()));
            entity.setUrlImagem(dto.getUrlImagem());
            entity.setCnpj(dto.getCnpj());
            entity.setIsFornecedor(dto.getIsFornecedor());
            Set<Tag> newTags = new LinkedHashSet<>();
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    newTags.add(tag);
                });
            }
            entity.setTags(newTags);
        } else {
            entity = new Usuario();
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setSenha(passwordEncoder.encode(dto.getSenha()));
            entity.setUrlImagem(dto.getUrlImagem());
            entity.setCnpj(dto.getCnpj());
            entity.setIsFornecedor(dto.getIsFornecedor());
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().stream().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    entity.addTag(tag);
                });
            }
        }
        return entity;
    }
}