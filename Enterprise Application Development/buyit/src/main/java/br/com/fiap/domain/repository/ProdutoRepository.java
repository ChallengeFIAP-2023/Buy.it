package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Categoria;
import br.com.fiap.domain.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class ProdutoRepository implements Repository<Produto, Long> {

    private static volatile ProdutoRepository instance;
    private final EntityManager manager;

    private ProdutoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static ProdutoRepository build(EntityManager manager) {
        ProdutoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (ProdutoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new ProdutoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Produto findById(Long id) {
        return manager.find(Produto.class, id);
    }

    public List<Produto> findByIdCategoria(Long id) {
        String jpql = "SELECT produto FROM Produto produto WHERE produto.categoria.id = :id";
        TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Produto> findByName(String nome) {
        String jpql = "SELECT produto FROM Produto produto  where upper(produto.nome) LIKE CONCAT('%',upper(:nome),'%')";
        TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<Produto> findAll() {
        return manager.createQuery("FROM Produto", Produto.class).getResultList();
    }

    @Override
    public Produto persist(Produto produto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            produto.setId(null);
            transaction.begin();
            Categoria categoria = manager.find(Categoria.class, produto.getCategoria().getId());
            produto.setCategoria(categoria);
            manager.persist(produto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return produto;
    }

    @Override
    public Produto update(Produto produto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Produto produto_buscado = findById(produto.getId());
            if (Objects.nonNull(produto_buscado)) {

                // CATEGORIA
                Categoria categoria = manager.find(Categoria.class, produto.getCategoria().getId());
                produto_buscado.setCategoria(categoria);

                // NOME
                if (Objects.nonNull(produto.getNome())) {
                    produto_buscado.setNome(produto.getNome());
                }

                // DESCRICAO
                produto_buscado.setDescricao(produto.getDescricao());

                // IMG_URL
                produto_buscado.setImgUrl(produto.getImgUrl());

                // MARCA
                if (Objects.nonNull(produto.getMarca())) {
                    produto_buscado.setMarca(produto.getMarca());
                }

                // MENOR_PRECO
                produto_buscado.setMenorPreco(produto.getMenorPreco());


                manager.merge(produto_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return produto;
    }

    @Override
    public boolean delete(Produto produto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(produto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}