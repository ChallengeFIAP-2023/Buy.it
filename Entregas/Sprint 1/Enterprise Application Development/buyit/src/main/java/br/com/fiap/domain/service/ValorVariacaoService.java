package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.ValorVariacao;
import br.com.fiap.domain.repository.ValorVariacaoRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class ValorVariacaoService implements Service<ValorVariacao, Long> {

    private static volatile ValorVariacaoService instance;

    private final ValorVariacaoRepository repo;

    private ValorVariacaoService(ValorVariacaoRepository repo) {
        this.repo = repo;
    }

    public static ValorVariacaoService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        ValorVariacaoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (ValorVariacaoService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                ValorVariacaoRepository avaliacaoRepository = ValorVariacaoRepository.build(factory.createEntityManager());
                instance = new ValorVariacaoService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<ValorVariacao> findAll() {
        return repo.findAll();
    }

    @Override
    public ValorVariacao findById(Long id) {
        return repo.findById(id);
    }

    public List<ValorVariacao> findByIdTipoVariacao(Long id) {
        if (Objects.isNull(id)) return null;
        return repo.findByIdTipoVariacao(id);
    }

    public List<ValorVariacao> findByName(String nome) {
        if (Objects.isNull(nome)) return null;
        return repo.findByName(nome);
    }

    @Override
    public ValorVariacao persist(ValorVariacao valor_variacao) {
        if (Objects.isNull(valor_variacao)) return null;
        return repo.persist(valor_variacao);
    }

    @Override
    public ValorVariacao update(ValorVariacao valor_variacao) {
        if (Objects.isNull(valor_variacao)) return null;
        return repo.update(valor_variacao);
    }

    @Override
    public boolean delete(ValorVariacao valor_variacao) {
        if (Objects.isNull(valor_variacao)) return false;
        return repo.delete(valor_variacao);
    }
}
