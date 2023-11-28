package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.dto.TipoContatoDTO;
import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.repository.TipoContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class TipoContatoService {

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    public Page<TipoContatoDTO> listAll(Pageable pageRequest) {
        return tipoContatoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    public TipoContatoDTO findById(Long id) {
        TipoContato tipoContato = findEntityById(id);
        return convertToDto(tipoContato);
    }

    public TipoContatoDTO create(TipoContatoDTO newData) {
        TipoContato entity = convertToEntity(newData);
        TipoContato savedEntity = tipoContatoRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public TipoContatoDTO update(Long id, TipoContatoDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        TipoContato updatedEntity = convertToEntity(updatedData);    
        TipoContato savedEntity = tipoContatoRepository.save(updatedEntity);
        return convertToDto(savedEntity);
    }
    

    public void delete(Long id) {
        TipoContato entity = findEntityById(id);
        tipoContatoRepository.delete(entity);
    }

    public TipoContato findEntityById(Long id) {
        return tipoContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(TipoContato) - TipoContato não encontrado(a) por ID: " + id));
    }

    private TipoContatoDTO convertToDto(TipoContato tipoContato) {
        TipoContatoDTO dto = new TipoContatoDTO();
        dto.setId(tipoContato.getId());
        dto.setNome(tipoContato.getNome());
        return dto;
    }

    private TipoContato convertToEntity(TipoContatoDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        TipoContato tipoContato = new TipoContato();
        if (dto.getId() != null)
            tipoContato.setId(dto.getId());
        tipoContato.setNome(dto.getNome());
        return tipoContato;
    }
}