package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Desconto;
import br.com.fiap.domain.repository.DescontoRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class DescontoService implements Service<Desconto, Long> {

    private static volatile DescontoService instance;

    private final DescontoRepository repo;

    private DescontoService(DescontoRepository repo) {
        this.repo = repo;
    }

    public static DescontoService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        DescontoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (DescontoService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                DescontoRepository avaliacaoRepository = DescontoRepository.build(factory.createEntityManager());
                instance = new DescontoService(avaliacaoRepository);
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

    public List<Desconto> findByIdEstoque(Long id) {
        if (Objects.isNull(id)) return null;
        return repo.findByIdEstoque(id);
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
