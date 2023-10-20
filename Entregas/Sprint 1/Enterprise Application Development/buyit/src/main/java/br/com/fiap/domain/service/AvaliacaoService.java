package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Avaliacao;
import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.repository.AvaliacaoRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class AvaliacaoService implements Service<Avaliacao, Long> {

    private static volatile AvaliacaoService instance;

    private final AvaliacaoRepository repo;

    private AvaliacaoService(AvaliacaoRepository repo) {
        this.repo = repo;
    }

    public static AvaliacaoService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        AvaliacaoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (AvaliacaoService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                AvaliacaoRepository avaliacaoRepository = AvaliacaoRepository.build(factory.createEntityManager());
                instance = new AvaliacaoService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Avaliacao> findAll() {
        return repo.findAll();
    }

    @Override
    public Avaliacao findById(Long id) {
        return repo.findById(id);
    }

    public Usuario findByIdUsuario(Long id_usuario) {
        if (Objects.isNull(id_usuario)) return null;
        return repo.findByIdUsuario(id_usuario);
    }

    @Override
    public Avaliacao persist(Avaliacao avaliacao) {
        if (Objects.isNull(avaliacao)) return null;
        return repo.persist(avaliacao);
    }

    @Override
    public Avaliacao update(Avaliacao avaliacao) {
        if (Objects.isNull(avaliacao)) return null;
        return repo.update(avaliacao);
    }

    @Override
    public boolean delete(Avaliacao avaliacao) {
        if (Objects.isNull(avaliacao)) return false;
        return repo.delete(avaliacao);
    }
}