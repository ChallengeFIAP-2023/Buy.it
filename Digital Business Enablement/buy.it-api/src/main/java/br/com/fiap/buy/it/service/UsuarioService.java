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
import java.util.Objects;
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
        Usuario usuario = findEntityById(id);
        return convertToDto(usuario);
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
        usuarioRepository.delete(entity);
    }

    public Usuario findEntityById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Usuario) - Usuario não encontrado(a) por ID: " + id));
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setIdPessoa(usuario.getPessoa() != null ? usuario.getPessoa().getId() : null);
        if (usuario.getTags() != null) {
            Set<Long> idsTags = usuario.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setIdsTags(idsTags);
        }
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Usuario usuario = new Usuario();
        if (dto.getId() != null)
            usuario.setId(dto.getId());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPessoa(pessoaService.findEntityById(dto.getIdPessoa()));
        if (dto.getIdsTags() != null) {
            dto.getIdsTags().stream().forEach(id -> {
                Tag tag = tagService.findEntityById(id);
                usuario.addTag(tag);
            });
        }
        else {
            usuario.setTags(new LinkedHashSet<>());
        }
        return usuario;
    }

    public Optional<Usuario> validarLogin(LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .filter(usuario -> usuario.getSenha().equals(loginDTO.getSenha()));
    }
}