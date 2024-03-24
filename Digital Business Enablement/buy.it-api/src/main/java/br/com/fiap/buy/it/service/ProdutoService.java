package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.ProdutoDTO;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.repository.ProdutoRepository;

import br.com.fiap.buy.it.model.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TagService tagService;

    public Page<ProdutoDTO> listAll(Pageable pageRequest) {
        Page<Produto> list = produtoRepository.findAll(pageRequest);
        return list.map(this::convertToDto);
    }

    public ProdutoDTO findById(Long id) {
        Produto entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public ProdutoDTO create(ProdutoDTO newData) {
        Produto entity = convertToEntity(newData);
        Produto savedEntity = produtoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Produto updatedEntity = convertToEntity(updatedData);    
        Produto savedEntity = produtoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    
    @Transactional
    public void delete(Long id) {
        Produto entity = findEntityById(id);
        if (entity.getTags() != null) {
            for (Tag tag : entity.getTags()) {
                tag.removeProduto(entity);
            }
        }
        produtoRepository.delete(entity);
    }

    public Produto findEntityById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Produto não encontrado(a) por ID: " + id));
    }

    public Page<ProdutoDTO> findByDepartamentoId(Long departamentoId, Pageable pageable) {
        if (departamentoId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID do Departamento não pode ser nulo.");
        }
        Page<Produto> list = produtoRepository.findByDepartamentoId(departamentoId, pageable);
        return list.map(this::convertToDto);
    }

    public Page<ProdutoDTO> findByTagId(Long tagId, Pageable pageable) {
        if (tagId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID da Tag não pode ser nulo.");
        }
        Page<Produto> list = produtoRepository.findByTagId(tagId, pageable);
        return list.map(this::convertToDto);
    }

    public Page<ProdutoDTO> findByNomeContainingIgnoreCase(String nome, Pageable pageable) {
        Page<Produto> list = produtoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        return list.map(this::convertToDto);
    }

    private ProdutoDTO convertToDto(Produto entity) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setMarca(entity.getMarca());
        dto.setCor(entity.getCor());
        dto.setTamanho(entity.getTamanho());
        dto.setMaterial(entity.getMaterial());
        dto.setObservacao(entity.getObservacao());
        dto.setIdDepartamento(entity.getDepartamento() != null ? entity.getDepartamento().getId() : null);
        if (entity.getTags() != null) {
            Set<Long> idsTags = entity.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setIdsTags(idsTags);
        }
        return dto;
    }

    private Produto convertToEntity(ProdutoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ProdutoDTO não pode ser nulo.");
        }
        Produto entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
            entity.setNome(dto.getNome());
            entity.setMarca(dto.getMarca());
            entity.setCor(dto.getCor());
            entity.setTamanho(dto.getTamanho());
            entity.setMaterial(dto.getMaterial());
            entity.setObservacao(dto.getObservacao());
            entity.setDepartamento(departamentoService.findEntityById(dto.getIdDepartamento()));
            Set<Tag> newTags = new LinkedHashSet<>();
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    newTags.add(tag);
                });
            }
            entity.setTags(newTags);
        } else {
            entity = new Produto();
            entity.setNome(dto.getNome());
            entity.setMarca(dto.getMarca());
            entity.setCor(dto.getCor());
            entity.setTamanho(dto.getTamanho());
            entity.setMaterial(dto.getMaterial());
            entity.setObservacao(dto.getObservacao());
            entity.setDepartamento(departamentoService.findEntityById(dto.getIdDepartamento()));
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    entity.addTag(tag);
                });
            }
        }
        return entity;
    }
}