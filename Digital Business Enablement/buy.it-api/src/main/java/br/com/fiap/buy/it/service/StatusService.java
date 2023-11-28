package br.com.fiap.buy.it.service;

import br.com.fiap.buy.it.model.Status;
import br.com.fiap.buy.it.repository.StatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Page<Status> listAll(Pageable pageRequest) {
        return statusRepository.findAll(pageRequest);
    }

    public Status findById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "(Status) não encontrado(a) por ID: " + id));
    }

    public Status create(Status newData) {
        return statusRepository.save(newData);
    }

    public Status update(Long id, Status updatedData) {
        if (!id.equals(updatedData.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "(Status) ID no corpo da solicitação não corresponde ao ID na URL.");
        }
        findById(id);
        updatedData.setId(id);
        return statusRepository.save(updatedData);
    }

    public void delete(Long id) {
        statusRepository.delete(findById(id));
    }
}