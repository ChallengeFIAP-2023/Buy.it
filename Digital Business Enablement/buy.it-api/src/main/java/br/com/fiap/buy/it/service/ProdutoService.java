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
        return produtoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public ProdutoDTO findById(Long id) {
        Produto produto = findEntityById(id);
        return convertToDto(produto);
    }

    public ProdutoDTO create(ProdutoDTO newData) {
        Produto entity = convertToEntity(newData);
        Produto savedEntity = produtoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public ProdutoDTO update(Long id, ProdutoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Produto updatedEntity = convertToEntity(updatedData);    
        Produto savedEntity = produtoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        Produto entity = findEntityById(id);
        produtoRepository.delete(entity);
    }

    public Produto findEntityById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Produto não encontrado(a) por ID: " + id));
    }

    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setMarca(produto.getMarca());
        dto.setCor(produto.getCor());
        dto.setTamanho(produto.getTamanho());
        dto.setMaterial(produto.getMaterial());
        dto.setObservacao(produto.getObservacao());
        dto.setIdDepartamento(produto.getDepartamento() != null ? produto.getDepartamento().getId() : null);
        if (produto.getTags() != null) {
            Set<Long> idsTags = produto.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setIdsTags(idsTags);
        }
        return dto;
    }

    private Produto convertToEntity(ProdutoDTO dto) {
        Produto produto;
        if (dto.getId() != null) {
            produto = findEntityById(dto.getId());
            Set<Tag> newTags = new LinkedHashSet<>();
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    newTags.add(tag);
                });
            }
            produto.setTags(newTags);
        } else {
            produto = new Produto();
            produto.setNome(dto.getNome());
            produto.setMarca(dto.getMarca());
            produto.setCor(dto.getCor());
            produto.setTamanho(dto.getTamanho());
            produto.setMaterial(dto.getMaterial());
            produto.setObservacao(dto.getObservacao());
            produto.setDepartamento(departamentoService.findEntityById(dto.getIdDepartamento()));
            if (dto.getIdsTags() != null) {
                dto.getIdsTags().forEach(id -> {
                    Tag tag = tagService.findEntityById(id);
                    produto.addTag(tag);
                });
            }
        }
        return produto;
    }
}