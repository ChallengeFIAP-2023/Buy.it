package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Estoque;
import br.com.fiap.domain.repository.EstoqueRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class EstoqueService implements Service<Estoque, Long> {

    private static volatile EstoqueService instance;

    private final EstoqueRepository repo;

    private EstoqueService(EstoqueRepository repo) {
        this.repo = repo;
    }

    public static EstoqueService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        EstoqueService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (EstoqueService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                EstoqueRepository avaliacaoRepository = EstoqueRepository.build(factory.createEntityManager());
                instance = new EstoqueService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Estoque> findAll() {
        return repo.findAll();
    }

    @Override
    public Estoque findById(Long id) {
        return repo.findById(id);
    }

    public List<Estoque> findByIdProduto(Long id_produto) {
        if (Objects.isNull(id_produto)) return null;
        return repo.findByIdProduto(id_produto);
    }

    public List<Estoque> findByIdValor_Variacao(Long id_estoque) {
        if (Objects.isNull(id_estoque)) return null;
        return repo.findByIdValor_Variacao(id_estoque);
    }

    public List<Estoque> findByIdUsuario(Long id_usuario) {
        if (Objects.isNull(id_usuario)) return null;
        return repo.findByIdUsuario(id_usuario);
    }

    @Override
    public Estoque persist(Estoque estoque) {
        if (Objects.isNull(estoque)) return null;
        return repo.persist(estoque);
    }

    @Override
    public Estoque update(Estoque estoque) {
        if (Objects.isNull(estoque)) return null;
        return repo.update(estoque);
    }

    @Override
    public boolean delete(Estoque estoque) {
        if (Objects.isNull(estoque)) return false;
        return repo.delete(estoque);
    }
}
