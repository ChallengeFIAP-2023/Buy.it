package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.FormaContatoDTO;
import br.com.fiap.buy.it.model.FormaContato;
import br.com.fiap.buy.it.repository.FormaContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class FormaContatoService {

    @Autowired
    private FormaContatoRepository formaContatoRepository;

    @Autowired
    private TipoContatoService tipoContatoService;

    @Autowired
    private PessoaService pessoaService;

    public Page<FormaContatoDTO> listAll(Pageable pageRequest) {
        return formaContatoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public FormaContatoDTO findById(Long id) {
        FormaContato formaContato = findEntityById(id);
        return convertToDto(formaContato);
    }

    public FormaContatoDTO create(FormaContatoDTO newData) {
        FormaContato entity = convertToEntity(newData);
        FormaContato savedEntity = formaContatoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public FormaContatoDTO update(Long id, FormaContatoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        FormaContato updatedEntity = convertToEntity(updatedData);    
        FormaContato savedEntity = formaContatoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        FormaContato entity = findEntityById(id);
        formaContatoRepository.delete(entity);
    }

    public FormaContato findEntityById(Long id) {
        return formaContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(FormaContato) - FormaContato não encontrado(a) por ID: " + id));
    }

    private FormaContatoDTO convertToDto(FormaContato formaContato) {
        FormaContatoDTO dto = new FormaContatoDTO();
        dto.setId(formaContato.getId());
        dto.setIdTipoContato(formaContato.getTipoContato() != null ? formaContato.getTipoContato().getId() : null);
        dto.setValor(formaContato.getValor());
        dto.setIdPessoa(formaContato.getPessoa() != null ? formaContato.getPessoa().getId() : null);
        return dto;
    }

    private FormaContato convertToEntity(FormaContatoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        if (dto.getIdTipoContato() == null) {
            throw new IllegalArgumentException("(FormaContato) ID TipoContato não pode ser nulo.");
        }
        if (dto.getIdPessoa() == null) {
            throw new IllegalArgumentException("(FormaContato) ID Pessoa não pode ser nulo.");
        }
        FormaContato formaContato = new FormaContato();
        if (dto.getId() != null)
        formaContato.setId(dto.getId());
        formaContato.setTipoContato(tipoContatoService.findEntityById(dto.getIdTipoContato()));
        formaContato.setValor(dto.getValor());
        formaContato.setPessoa(pessoaService.findEntityById(dto.getIdPessoa()));
        return formaContato;
    }
}