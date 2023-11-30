package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.TagDTO;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.TagRepository;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.repository.ProdutoRepository;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<TagDTO> listAll(Pageable pageRequest) {
        return tagRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public TagDTO findById(Long id) {
        Tag entity = findEntityById(id);
        return convertToDto(entity);
    }

    public TagDTO create(TagDTO newData) {
        Tag entity = convertToEntity(newData);
        Tag savedEntity = tagRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public TagDTO update(Long id, TagDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Tag updatedEntity = convertToEntity(updatedData);    
        Tag savedEntity = tagRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    
    public void delete(Long id) {
        Tag entity = findEntityById(id);
        if (entity.getDepartamentos() != null) {
            for (Departamento departamento : entity.getDepartamentos()) {
                departamento.removeTag(entity);
            }
        }
        if (entity.getProdutos() != null) {
            for (Produto produto : entity.getProdutos()) {
                produto.removeTag(entity);
            }
        }
        if (entity.getUsuarios() != null) {
            for (Usuario usuario : entity.getUsuarios()) {
                usuario.removeTag(entity);
            }
        }
        tagRepository.delete(entity);
    }

    public Tag findEntityById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Tag não encontrado(a) por ID: " + id));
    }

    private TagDTO convertToDto(Tag entity) {
        TagDTO dto = new TagDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        if (entity.getDepartamentos() != null) {
            Set<Long> idsDepartamentos = entity.getDepartamentos().stream()
                    .map(Departamento::getId)
                    .collect(Collectors.toSet());
            dto.setIdsDepartamentos(idsDepartamentos);
        }
        if (entity.getProdutos() != null) {
            Set<Long> idsProdutos = entity.getProdutos().stream()
                    .map(Produto::getId)
                    .collect(Collectors.toSet());
            dto.setIdsProdutos(idsProdutos);
        }
        if (entity.getUsuarios() != null) {
            Set<Long> idsUsuarios = entity.getUsuarios().stream()
                    .map(Usuario::getId)
                    .collect(Collectors.toSet());
            dto.setIdsUsuarios(idsUsuarios);
        }
        return dto;
    }

    private Tag convertToEntity(TagDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - TagDTO não pode ser nulo.");
        }
        Tag entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new Tag();
        }
        entity.setNome(dto.getNome());
        Set<Departamento> newDepartamentos = new LinkedHashSet<>();
        if (dto.getIdsDepartamentos() != null) {
            dto.getIdsDepartamentos().forEach(id -> {
                Departamento departamento = departamentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado com ID: " + id));
                newDepartamentos.add(departamento);
            });
        }
        entity.setDepartamentos(newDepartamentos);
        Set<Produto> newProdutos = new LinkedHashSet<>();
        if (dto.getIdsProdutos() != null) {
            dto.getIdsProdutos().forEach(id -> {
                Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado com ID: " + id));
                newProdutos.add(produto);
            });
        }
        entity.setProdutos(newProdutos);
        Set<Usuario> newUsuarios = new LinkedHashSet<>();
        if (dto.getIdsUsuarios() != null) {
            dto.getIdsUsuarios().forEach(id -> {
                Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado com ID: " + id));
                newUsuarios.add(usuario);
            });
        }
        entity.setUsuarios(newUsuarios);
        return entity;
    }
}