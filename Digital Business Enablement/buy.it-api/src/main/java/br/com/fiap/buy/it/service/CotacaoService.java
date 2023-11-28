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
        Cotacao cotacao = findEntityById(id);
        return convertToDto(cotacao);
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

    private CotacaoDTO convertToDto(Cotacao cotacao) {
        CotacaoDTO dto = new CotacaoDTO();
        dto.setId(cotacao.getId());
        dto.setDataAbertura(cotacao.getDataAbertura());
        dto.setIdComprador(cotacao.getComprador() != null ? cotacao.getComprador().getId() : null);
        dto.setIdProduto(cotacao.getProduto() != null ? cotacao.getProduto().getId() : null);
        dto.setQuantidadeProduto(cotacao.getQuantidadeProduto());
        dto.setValorProduto(cotacao.getValorProduto());
        dto.setIdStatus(cotacao.getStatus() != null ? cotacao.getStatus().getId() : null);
        dto.setPrioridadeEntrega(cotacao.getPrioridadeEntrega());
        dto.setPrioridadeQualidade(cotacao.getPrioridadeQualidade());
        dto.setPrioridadePreco(cotacao.getPrioridadePreco());
        dto.setPrazo(cotacao.getPrazo());
        dto.setDataFechamento(cotacao.getDataFechamento());
        return dto;
    }

    public Cotacao findEntityById(Long id) {
        return cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Cotacao) - Cotacao não encontrado(a) por ID: " + id));
    }

    private Cotacao convertToEntity(CotacaoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getIdComprador() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Comprador não pode ser nulo.");
        }
        if (dto.getIdProduto() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Produto não pode ser nulo.");
        }
        if (dto.getIdStatus() == null) {
            throw new IllegalArgumentException("(Cotacao) ID Status não pode ser nulo.");
        }
        Cotacao cotacao = new Cotacao();
        if (dto.getId() != null)
            cotacao.setId(dto.getId());
        cotacao.setDataAbertura(dto.getDataAbertura());
        cotacao.setComprador(usuarioService.findEntityById(dto.getIdComprador()));
        cotacao.setProduto(produtoService.findEntityById(dto.getIdProduto()));
        cotacao.setQuantidadeProduto(dto.getQuantidadeProduto());
        cotacao.setValorProduto(dto.getValorProduto());
        cotacao.setStatus(statusService.findEntityById(dto.getIdStatus()));
        cotacao.setPrioridadeEntrega(dto.getPrioridadeEntrega());
        cotacao.setPrioridadeQualidade(dto.getPrioridadeQualidade());
        cotacao.setPrioridadePreco(dto.getPrioridadePreco());
        cotacao.setPrazo(dto.getPrazo());
        cotacao.setDataFechamento(dto.getDataFechamento());
        return cotacao;
    }
}