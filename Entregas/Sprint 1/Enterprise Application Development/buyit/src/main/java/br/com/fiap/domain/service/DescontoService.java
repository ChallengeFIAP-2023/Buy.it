package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Desconto;
import br.com.fiap.domain.entity.Estoque;
import br.com.fiap.domain.repository.DescontoRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class DescontoService implements Service<Desconto, Long> {

    private static volatile DescontoService instance;

    private final DescontoRepository repo;

    private DescontoService(DescontoRepository repo) {
        this.repo = repo;
    }

    public static DescontoService build(EntityManager manager) {
        DescontoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (DescontoService.class) {
            if (Objects.isNull(instance)) {
                DescontoRepository descontoRepository = DescontoRepository.getInstance(manager);
                instance = new DescontoService(descontoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Desconto> findAll() {
        return repo.findAll();
    }

    @Override
    public Desconto findById(Long id) {
        return repo.findById(id);
    }

    public Estoque findByIdEstoque(Long id_estoque) {
        if (Objects.isNull(id_estoque)) return null;
        return repo.findByIdEstoque(id_estoque);
    }

    @Override
    public Desconto persist(Desconto desconto) {
        if (Objects.isNull(desconto)) return null;
        return repo.persist(desconto);
    }

    @Override
    public Desconto update(Desconto desconto) {
        if (Objects.isNull(desconto)) return null;
        return repo.update(desconto);
    }

    @Override
    public boolean delete(Desconto desconto) {
        if (Objects.isNull(desconto)) return false;
        return repo.delete(desconto);
    }
}
