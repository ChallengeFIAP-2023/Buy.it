package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.TipoContato;
import br.com.fiap.buy.it.repository.TipoContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TipoContatoService {

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    public Page<TipoContato> listAll(Pageable pageRequest) {
        return tipoContatoRepository.findAll(pageRequest);
    }

    public TipoContato findById(Long id) {
        return tipoContatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(TipoContato) não encontrado(a) por ID: " + id));
    }

    public TipoContato create(TipoContato newData) {
        return tipoContatoRepository.save(newData);
    }

    public TipoContato update(Long id, TipoContato updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(TipoContato) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        return tipoContatoRepository.save(updatedData);
    }

    public void delete(Long id) {
        tipoContatoRepository.delete(findById(id));
    }
}