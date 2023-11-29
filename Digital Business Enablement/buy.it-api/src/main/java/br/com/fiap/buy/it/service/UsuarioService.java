package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.UsuarioDTO;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.UsuarioRepository;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.dto.LoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private TagService tagService;

    public Page<UsuarioDTO> listAll(Pageable pageRequest) {
        return usuarioRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public UsuarioDTO findById(Long id) {
        Usuario entity = findEntityById(id);
        return convertToDto(entity);
    }

    public UsuarioDTO create(UsuarioDTO newData) {
        Usuario entity = convertToEntity(newData);
        Usuario savedEntity = usuarioRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public UsuarioDTO update(Long id, UsuarioDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Usuario updatedEntity = convertToEntity(updatedData);    
        Usuario savedEntity = usuarioRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }

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

    private UsuarioDTO convertToDto(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setSenha(entity.getSenha());
        dto.setIdPessoa(entity.getPessoa() != null ? entity.getPessoa().getId() : null);
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
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity.setPessoa(pessoaService.findEntityById(dto.getIdPessoa()));
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
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity.setPessoa(pessoaService.findEntityById(dto.getIdPessoa()));
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().stream().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    entity.addTag(tag);
                });
            }
        }
        return entity;
    }

    public Optional<Usuario> validarLogin(LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .filter(entity -> entity.getSenha().equals(loginDTO.getSenha()));
    }
}