package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Categoria;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class ProdutoService implements Service<Produto, Long> {

    private static volatile ProdutoService instance;
    private final ProdutoRepository repo;

    private ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public static ProdutoService build(EntityManager manager) {
        ProdutoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (ProdutoService.class) {
            if (Objects.isNull(instance)) {
                ProdutoRepository produtoRepository = ProdutoRepository.getInstance(manager);
                instance = new ProdutoService(produtoRepository);
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

    public Categoria findByIdCategoria(Long id_categoria) {
        if (Objects.isNull(id_categoria)) return null;
        return repo.findByIdCategoria(id_categoria);
    }

    public List<Produto> findByName(String name) {
        if (Objects.isNull(name)) return null;
        return repo.findByName(name);
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
