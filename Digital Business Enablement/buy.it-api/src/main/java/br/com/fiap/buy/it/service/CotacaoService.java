package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.CotacaoDTO;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.repository.CotacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<CotacaoDTO> listAll(Pageable pageRequest) {
        return cotacaoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public CotacaoDTO findById(Long id) {
        Cotacao entity = findEntityById(id);
        return convertToDto(entity);
    }

    public CotacaoDTO create(CotacaoDTO newData) {
        Cotacao entity = convertToEntity(newData);
        Cotacao savedEntity = cotacaoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public CotacaoDTO update(Long id, CotacaoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Cotacao updatedEntity = convertToEntity(updatedData);    
        Cotacao savedEntity = cotacaoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }

    public void delete(Long id) {
        Cotacao entity = findEntityById(id);
        cotacaoRepository.delete(entity);
    }

    public Cotacao findEntityById(Long id) {
        return cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Cotacao não encontrado(a) por ID: " + id));
    }

    private CotacaoDTO convertToDto(Cotacao entity) {
        CotacaoDTO dto = new CotacaoDTO();
        dto.setId(entity.getId());
        dto.setDataAbertura(entity.getDataAbertura());
        dto.setIdComprador(entity.getComprador() != null ? entity.getComprador().getId() : null);
        dto.setIdProduto(entity.getProduto() != null ? entity.getProduto().getId() : null);
        dto.setQuantidadeProduto(entity.getQuantidadeProduto());
        dto.setValorProduto(entity.getValorProduto());
        dto.setIdStatus(entity.getStatus() != null ? entity.getStatus().getId() : null);
        dto.setPrioridadeEntrega(entity.getPrioridadeEntrega());
        dto.setPrioridadeQualidade(entity.getPrioridadeQualidade());
        dto.setPrioridadePreco(entity.getPrioridadePreco());
        dto.setPrazo(entity.getPrazo());
        dto.setDataFechamento(entity.getDataFechamento());
        return dto;
    }

    private Cotacao convertToEntity(CotacaoDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - CotacaoDTO não pode ser nulo.");
        }
        Cotacao entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new Cotacao();
        }
        if (dto.getIdComprador() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Comprador não pode ser nulo.");
        }
        if (dto.getIdProduto() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Produto não pode ser nulo.");
        }
        if (dto.getIdStatus() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Status não pode ser nulo.");
        }
        entity.setDataAbertura(dto.getDataAbertura());
        entity.setComprador(usuarioService.findEntityById(dto.getIdComprador()));
        entity.setProduto(produtoService.findEntityById(dto.getIdProduto()));
        entity.setQuantidadeProduto(dto.getQuantidadeProduto());
        entity.setValorProduto(dto.getValorProduto());
        entity.setStatus(statusService.findEntityById(dto.getIdStatus()));
        entity.setPrioridadeEntrega(dto.getPrioridadeEntrega());
        entity.setPrioridadeQualidade(dto.getPrioridadeQualidade());
        entity.setPrioridadePreco(dto.getPrioridadePreco());
        entity.setPrazo(dto.getPrazo());
        entity.setDataFechamento(dto.getDataFechamento());
        return entity;
    }
}