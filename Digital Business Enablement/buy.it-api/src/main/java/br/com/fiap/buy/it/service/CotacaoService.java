package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.CotacaoDTO;
import br.com.fiap.buy.it.dto.InfoCotacaoDTO;
import br.com.fiap.buy.it.model.Cotacao;
import br.com.fiap.buy.it.repository.CotacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

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
        Page<Cotacao> cotacoes = cotacaoRepository.findAll(pageRequest);
        return cotacoes.map(this::convertToDto);
    }

    public CotacaoDTO findById(Long id) {
        Cotacao entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public CotacaoDTO create(CotacaoDTO newData) {
        Cotacao entity = convertToEntity(newData);
        Cotacao savedEntity = cotacaoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public CotacaoDTO update(Long id, CotacaoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Cotacao updatedEntity = convertToEntity(updatedData);    
        Cotacao savedEntity = cotacaoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public void delete(Long id) {
        Cotacao entity = findEntityById(id);
        cotacaoRepository.delete(entity);
    }

    public Cotacao findEntityById(Long id) {
        return cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Cotacao não encontrado(a) por ID: " + id));
    }

    public Page<CotacaoDTO> findByCompradorId(Long userId, Pageable pageable) {
        if (userId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID do Usuário não pode ser nulo.");
        }
        Page<Cotacao> cotacoes = cotacaoRepository.findByCompradorId(userId, pageable);
        return cotacoes.map(this::convertToDto);
    }

    public Page<CotacaoDTO> findByProdutoId(Long produtoId, Pageable pageable) {
        if (produtoId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID do Produto não pode ser nulo.");
        }
        Page<Cotacao> cotacoes = cotacaoRepository.findByProdutoId(produtoId, pageable);
        return cotacoes.map(this::convertToDto);
    }

    public Page<CotacaoDTO> findByStatusId(Long statusId, Pageable pageable) {
        if (statusId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID do Status não pode ser nulo.");
        }
        Page<Cotacao> cotacoes = cotacaoRepository.findByStatusId(statusId, pageable);
        return cotacoes.map(this::convertToDto);
    }

    public InfoCotacaoDTO getInfoByProdutoId(Long idProduto) {
        Optional<Object> resultadoRaw = cotacaoRepository.getInfoByProdutoId(idProduto);
        if (resultadoRaw.isPresent()) {
            Object[] resultado = (Object[]) resultadoRaw.get();
            BigDecimal minValor = resultado[0] != null ? new BigDecimal(resultado[0].toString()) : null;
            BigDecimal avgValor = resultado[1] != null ? new BigDecimal(resultado[1].toString()) : null;
            BigDecimal maxValor = resultado[2] != null ? new BigDecimal(resultado[2].toString()) : null;
            return new InfoCotacaoDTO(minValor, avgValor, maxValor);
        } else {
            return new InfoCotacaoDTO(null, null, null);
        }
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