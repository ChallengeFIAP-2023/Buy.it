package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.repository.ProdutoRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class ProdutoService implements Service<Produto, Long> {

    private static volatile ProdutoService instance;

    private final ProdutoRepository repo;

    private ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public static ProdutoService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        ProdutoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (ProdutoService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                ProdutoRepository avaliacaoRepository = ProdutoRepository.build(factory.createEntityManager());
                instance = new ProdutoService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Produto> findAll() {
        return repo.findAll();
    }

    @Override
    public Produto findById(Long id) {
        return repo.findById(id);
    }

    public List<Produto> findByIdCategoria(Long id) {
        if (Objects.isNull(id)) return null;
        return repo.findByIdCategoria(id);
    }

    public List<Produto> findByName(String nome) {
        if (Objects.isNull(nome)) return null;
        return repo.findByName(nome);
    }

    @Override
    public Produto persist(Produto produto) {
        if (Objects.isNull(produto)) return null;
        return repo.persist(produto);
    }

    @Override
    public Produto update(Produto produto) {
        if (Objects.isNull(produto)) return null;
        return repo.update(produto);
    }

    @Override
    public boolean delete(Produto produto) {
        if (Objects.isNull(produto)) return false;
        return repo.delete(produto);
    }
}
