package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Log;
import br.com.fiap.domain.repository.LogRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class LogService implements Service<Log, Long> {

    private static volatile LogService instance;

    private final LogRepository repo;

    private LogService(LogRepository repo) {
        this.repo = repo;
    }

    public static LogService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        LogService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (LogService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                LogRepository avaliacaoRepository = LogRepository.build(factory.createEntityManager());
                instance = new LogService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Log> findAll() {
        return repo.findAll();
    }

    @Override
    public Log findById(Long id) {
        return repo.findById(id);
    }

    public List<Log> findByIdPedido(Long id_pedido) {
        if (Objects.isNull(id_pedido)) return null;
        return repo.findByIdPedido(id_pedido);
    }

    public List<Log> findByName(String name) {
        if (Objects.isNull(name)) return null;
        return repo.findByName(name);
    }

    @Override
    public Log persist(Log log) {
        if (Objects.isNull(log)) return null;
        return repo.persist(log);
    }

    @Override
    public Log update(Log log) {
        if (Objects.isNull(log)) return null;
        return repo.update(log);
    }

    @Override
    public boolean delete(Log log) {
        if (Objects.isNull(log)) return false;
        return repo.delete(log);
    }
}
