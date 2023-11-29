package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.HistoricoDTO;
import br.com.fiap.buy.it.model.Historico;
import br.com.fiap.buy.it.repository.HistoricoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private CotacaoService cotacaoService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<HistoricoDTO> listAll(Pageable pageRequest) {
        return historicoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public HistoricoDTO findById(Long id) {
        Historico historico = findEntityById(id);
        return convertToDto(historico);
    }

    public HistoricoDTO create(HistoricoDTO newData) {
        Historico entity = convertToEntity(newData);
        Historico savedEntity = historicoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public HistoricoDTO update(Long id, HistoricoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Historico updatedEntity = convertToEntity(updatedData);    
        Historico savedEntity = historicoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    
    public void delete(Long id) {
        Historico entity = findEntityById(id);
        historicoRepository.delete(entity);
    }

    public Historico findEntityById(Long id) {
        return historicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Historico não encontrado(a) por ID: " + id));
    }

    private HistoricoDTO convertToDto(Historico historico) {
        HistoricoDTO dto = new HistoricoDTO();
        dto.setId(historico.getId());
        dto.setIdCotacao(historico.getCotacao() != null ? historico.getCotacao().getId() : null);
        dto.setIdFornecedor(historico.getFornecedor() != null ? historico.getFornecedor().getId() : null);
        dto.setIdStatus(historico.getStatus() != null ? historico.getStatus().getId() : null);
        dto.setRecusadoPorProduto(historico.getRecusadoPorProduto());
        dto.setRecusadoPorQuantidade(historico.getRecusadoPorQuantidade());
        dto.setRecusadoPorPreco(historico.getRecusadoPorPreco());
        dto.setRecusadoPorPrazo(historico.getRecusadoPorPrazo());
        dto.setDescricao(historico.getDescricao());
        dto.setData(historico.getData());
        dto.setValorOfertado(historico.getValorOfertado());
        return dto;
    }

    private Historico convertToEntity(HistoricoDTO dto) {
        Historico historico;
        if (dto.getId() != null) {
            historico = findEntityById(dto.getId());
        } else {
            historico = new Historico();
        }
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - HistoricoDTO não pode ser nulo.");
        }
        if (dto.getIdCotacao() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Cotacao não pode ser nulo.");
        }
        if (dto.getIdFornecedor() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Fornecedor não pode ser nulo.");
        }
        if (dto.getIdStatus() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Status não pode ser nulo.");
        }
        historico.setCotacao(cotacaoService.findEntityById(dto.getIdCotacao()));
        historico.setFornecedor(usuarioService.findEntityById(dto.getIdFornecedor()));
        historico.setStatus(statusService.findEntityById(dto.getIdStatus()));
        historico.setRecusadoPorProduto(dto.getRecusadoPorProduto());
        historico.setRecusadoPorQuantidade(dto.getRecusadoPorQuantidade());
        historico.setRecusadoPorPreco(dto.getRecusadoPorPreco());
        historico.setRecusadoPorPrazo(dto.getRecusadoPorPrazo());
        historico.setDescricao(dto.getDescricao());
        historico.setData(dto.getData());
        historico.setValorOfertado(dto.getValorOfertado());
        return historico;
    }
}