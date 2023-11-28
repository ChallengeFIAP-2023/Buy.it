package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.TagDTO;
import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Produto;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.model.Usuario;
import br.com.fiap.buy.it.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<TagDTO> listAll(Pageable pageRequest) {
        return tagRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public TagDTO findById(Long id) {
        Tag tag = findEntityById(id);
        return convertToDto(tag);
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
        tagRepository.delete(entity);
    }

    public Tag findEntityById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Tag) - Tag não encontrado(a) por ID: " + id));
    }

    private TagDTO convertToDto(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setId(tag.getId());
        dto.setNome(tag.getNome());
        Set<Long> idsDepartamentos = tag.getDepartamentos().stream()
                .map(Departamento::getId)
                .collect(Collectors.toSet());
        dto.setIdsDepartamentos(idsDepartamentos);
        Set<Long> idsProdutos = tag.getProdutos().stream()
                .map(Produto::getId)
                .collect(Collectors.toSet());
        dto.setIdsProdutos(idsProdutos);
        Set<Long> idsUsuarios = tag.getUsuarios().stream()
                .map(Usuario::getId)
                .collect(Collectors.toSet());
        dto.setIdsUsuarios(idsUsuarios);
        return dto;
    }

    private Tag convertToEntity(TagDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Tag) ID Tag não pode ser nulo.");
        }
        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setNome(dto.getNome());
        dto.getIdsDepartamentos().stream().forEach(id -> {
            Departamento departamento = departamentoService.findEntityById(id);
            tag.addDepartamento(departamento);
        });
        dto.getIdsProdutos().stream().forEach(id -> {
            Produto produto = produtoService.findEntityById(id);
            tag.addProduto(produto);
        });
        dto.getIdsUsuarios().stream().forEach(id -> {
            Usuario usuario = usuarioService.findEntityById(id);
            tag.addUsuario(usuario);
        });
        return tag;
    }
}