package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Tipo_Variacao;
import br.com.fiap.domain.entity.Valor_Variacao;
import br.com.fiap.domain.repository.Valor_VariacaoRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class Valor_VariacaoService implements Service<Valor_Variacao, Long> {

    private static volatile Valor_VariacaoService instance;

    private final Valor_VariacaoRepository repo;

    private Valor_VariacaoService(Valor_VariacaoRepository repo) {
        this.repo = repo;
    }

    public static Valor_VariacaoService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        Valor_VariacaoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (Valor_VariacaoService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                Valor_VariacaoRepository avaliacaoRepository = Valor_VariacaoRepository.build(factory.createEntityManager());
                instance = new Valor_VariacaoService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Valor_Variacao> findAll() {
        return repo.findAll();
    }

    @Override
    public Valor_Variacao findById(Long id) {
        return repo.findById(id);
    }

    public List<Valor_Variacao> findByName(String name) {
        if (Objects.isNull(name)) return null;
        return repo.findByName(name);
    }

    public Tipo_Variacao findByIdTipoVariacao(Long id) {
        if (Objects.isNull(id)) return null;
        return repo.findByIdTipoVariacao(id);
    }

    @Override
    public Valor_Variacao persist(Valor_Variacao valor_variacao) {
        if (Objects.isNull(valor_variacao)) return null;
        return repo.persist(valor_variacao);
    }

    @Override
    public Valor_Variacao update(Valor_Variacao valor_variacao) {
        if (Objects.isNull(valor_variacao)) return null;
        return repo.update(valor_variacao);
    }

    @Override
    public boolean delete(Valor_Variacao valor_variacao) {
        if (Objects.isNull(valor_variacao)) return false;
        return repo.delete(valor_variacao);
    }
}
