package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.TipoVariacao;
import br.com.fiap.domain.repository.TipoVariacaoRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class TipoVariacaoService implements Service<TipoVariacao, Long> {

    private static volatile TipoVariacaoService instance;

    private final TipoVariacaoRepository repo;

    private TipoVariacaoService(TipoVariacaoRepository repo) {
        this.repo = repo;
    }

    public static TipoVariacaoService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        TipoVariacaoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (TipoVariacaoService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                TipoVariacaoRepository avaliacaoRepository = TipoVariacaoRepository.build(factory.createEntityManager());
                instance = new TipoVariacaoService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<TipoVariacao> findAll() {
        return repo.findAll();
    }

    @Override
    public TipoVariacao findById(Long id) {
        return repo.findById(id);
    }

    public List<TipoVariacao> findByName(String nome) {
        if (Objects.isNull(nome)) return null;
        return repo.findByName(nome);
    }

    @Override
    public TipoVariacao persist(TipoVariacao tipo_variacao) {
        if (Objects.isNull(tipo_variacao)) return null;
        return repo.persist(tipo_variacao);
    }

    @Override
    public TipoVariacao update(TipoVariacao tipo_variacao) {
        if (Objects.isNull(tipo_variacao)) return null;
        return repo.update(tipo_variacao);
    }

    @Override
    public boolean delete(TipoVariacao tipo_variacao) {
        if (Objects.isNull(tipo_variacao)) return false;
        return repo.delete(tipo_variacao);
    }
}
