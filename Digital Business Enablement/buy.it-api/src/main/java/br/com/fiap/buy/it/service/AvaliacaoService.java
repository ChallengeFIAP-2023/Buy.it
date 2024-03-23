package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.AvaliacaoDTO;
import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.repository.AvaliacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CotacaoService cotacaoService;

    public Page<AvaliacaoDTO> listAll(Pageable pageRequest) {
        Page<Avaliacao> list = avaliacaoRepository.findAll(pageRequest);
        return list.map(this::convertToDto);
    }

    public AvaliacaoDTO findById(Long id) {
        Avaliacao entity = findEntityById(id);
        return convertToDto(entity);
    }

    @Transactional
    public AvaliacaoDTO create(AvaliacaoDTO newData) {
        Avaliacao entity = convertToEntity(newData);
        Avaliacao savedEntity = avaliacaoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    @Transactional
    public AvaliacaoDTO update(Long id, AvaliacaoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Avaliacao updatedEntity = convertToEntity(updatedData);    
        Avaliacao savedEntity = avaliacaoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    
    @Transactional
    public void delete(Long id) {
        Avaliacao entity = findEntityById(id);
        avaliacaoRepository.delete(entity);
    }

    public Avaliacao findEntityById(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Avaliacao não encontrado(a) por ID: " + id));
    }

    public Page<AvaliacaoDTO> findByCotacaoId(Long cotacaoId, Pageable pageable) {
        if (cotacaoId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID da Cotação não pode ser nulo.");
        }
        Page<Avaliacao> list = avaliacaoRepository.findByCotacaoId(cotacaoId, pageable);
        return list.map(this::convertToDto);
    }

    public Page<AvaliacaoDTO> findByUsuarioId(Long usuarioId, Pageable pageable) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID do Usuário não pode ser nulo.");
        }
        Page<Avaliacao> list = avaliacaoRepository.findByUsuarioId(usuarioId, pageable);
        return list.map(this::convertToDto);
    }
    
    private AvaliacaoDTO convertToDto(Avaliacao entity) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(entity.getId());
        dto.setIdCotacao(entity.getCotacao() != null ? entity.getCotacao().getId() : null);
        dto.setData(entity.getData());
        dto.setNotaEntrega(entity.getNotaEntrega());
        dto.setNotaQualidade(entity.getNotaQualidade());
        dto.setNotaPreco(entity.getNotaPreco());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    private Avaliacao convertToEntity(AvaliacaoDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - AvaliacaoDTO não pode ser nulo.");
        }
        Avaliacao entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
        } else {
            entity = new Avaliacao();
        }
        if (dto.getIdCotacao() == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - ID Cotacao não pode ser nulo.");
        }
        entity.setCotacao(cotacaoService.findEntityById(dto.getIdCotacao()));
        entity.setData(dto.getData());
        entity.setNotaEntrega(dto.getNotaEntrega());
        entity.setNotaQualidade(dto.getNotaQualidade());
        entity.setNotaPreco(dto.getNotaPreco());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}