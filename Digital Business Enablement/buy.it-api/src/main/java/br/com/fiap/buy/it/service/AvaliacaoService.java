package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.AvaliacaoDTO;
import br.com.fiap.buy.it.model.Avaliacao;
import br.com.fiap.buy.it.repository.AvaliacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CotacaoService cotacaoService;

    public Page<AvaliacaoDTO> listAll(Pageable pageRequest) {
        return avaliacaoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public AvaliacaoDTO findById(Long id) {
        Avaliacao avaliacao = findEntityById(id);
        return convertToDto(avaliacao);
    }

    public AvaliacaoDTO create(AvaliacaoDTO newData) {
        Avaliacao entity = convertToEntity(newData);
        Avaliacao savedEntity = avaliacaoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public AvaliacaoDTO update(Long id, AvaliacaoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Avaliacao updatedEntity = convertToEntity(updatedData);    
        Avaliacao savedEntity = avaliacaoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        Avaliacao entity = findEntityById(id);
        avaliacaoRepository.delete(entity);
    }

    public Avaliacao findEntityById(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(Avaliacao) - Avaliacao não encontrado(a) por ID: " + id));
    }

    private AvaliacaoDTO convertToDto(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setIdCotacao(avaliacao.getCotacao() != null ? avaliacao.getCotacao().getId() : null);
        dto.setData(avaliacao.getData());
        dto.setNotaEntrega(avaliacao.getNotaEntrega());
        dto.setNotaQualidade(avaliacao.getNotaQualidade());
        dto.setNotaPreco(avaliacao.getNotaPreco());
        dto.setDescricao(avaliacao.getDescricao());
        return dto;
    }

    private Avaliacao convertToEntity(AvaliacaoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("(Avaliacao) ID Avaliacao não pode ser nulo.");
        }
        if (dto.getIdCotacao() == null) {
            throw new IllegalArgumentException("(Avaliacao) ID Cotacao não pode ser nulo.");
        }
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(dto.getId());
        if (dto.getIdCotacao() != null)
            avaliacao.setCotacao(cotacaoService.findEntityById(dto.getIdCotacao()));
        avaliacao.setData(dto.getData());
        avaliacao.setNotaEntrega(dto.getNotaEntrega());
        avaliacao.setNotaQualidade(dto.getNotaQualidade());
        avaliacao.setNotaPreco(dto.getNotaPreco());
        avaliacao.setDescricao(dto.getDescricao());
        return avaliacao;
    }
}