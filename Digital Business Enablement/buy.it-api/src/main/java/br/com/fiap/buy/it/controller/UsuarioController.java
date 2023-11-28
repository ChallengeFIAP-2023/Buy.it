package br.com.fiap.buy.it.controller;

import br.com.fiap.buy.it.dto.UsuarioDTO;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.service.UsuarioService;
import br.com.fiap.buy.it.service.PessoaService;
import br.com.fiap.buy.it.service.TagService;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Objects;

@RestController
@RequestMapping("usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public Page<UsuarioDTO> listAll(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("(" + getClass().getSimpleName() + ") - Buscando todos(as)");
        return usuarioService.listAll(pageable).map(this::convertToDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Exibindo por ID: " + id);
        return ResponseEntity.ok(convertToDto(usuarioService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody @Valid UsuarioDTO newData) {
        log.info("(" + getClass().getSimpleName() + ") - Cadastrando: " + newData);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(usuarioService.create(convertToEntity(newData))));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody @Valid UsuarioDTO updatedData) {
        log.info("(" + getClass().getSimpleName() + ") - Atualizando por ID: " + id);
        return ResponseEntity.ok(convertToDto(usuarioService.update(id, convertToEntity(updatedData))));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("(" + getClass().getSimpleName() + ") - Deletando por ID: " + id);
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setIdPessoa(usuario.getPessoa() != null ? usuario.getPessoa().getId() : null);
        Set<Long> idsTags = usuario.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());
        dto.setIdsTags(idsTags);
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        if (dto.getIdPessoa() != null)
            usuario.setPessoa(pessoaService.findById(dto.getIdPessoa()));
        dto.getIdsTags().stream().forEach(id -> {
            Tag tag = tagService.findById(id);
            usuario.addTag(tag);
        });
        return usuario;
    }
}