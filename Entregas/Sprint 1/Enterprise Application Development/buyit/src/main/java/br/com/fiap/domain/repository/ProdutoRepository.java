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

    public List<Produto> findByIdCategoria(Long id_categoria) {
        String jpql = "SELECT produto FROM Produto produto WHERE produto.id_categoria = :id_categoria";
        TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
        query.setParameter("id_categoria", id_categoria);
        return query.getResultList();
    }

    public List<Produto> findByName(String name) {
        String jpql = "SELECT produto FROM Produto produto  where upper(produto.nm_produto) LIKE CONCAT('%',upper(:nm_produto),'%')";
        TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
        query.setParameter("nm_produto", name);
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
            produto.setId_produto(null);
            transaction.begin();
            Categoria categoria = manager.find(Categoria.class, produto.getId_categoria().getId_categoria());
            produto.setId_categoria(categoria);
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
            Produto produto_buscado = findById(produto.getId_produto());
            if (Objects.nonNull(produto_buscado)) {

                // ID_CATEGORIA
                Categoria categoria = manager.find(Categoria.class, produto.getId_categoria().getId_categoria());
                produto_buscado.setId_categoria(categoria);

                // NM_PRODUTO
                if (Objects.nonNull(produto.getNm_produto())) {
                    produto_buscado.setNm_produto(produto.getNm_produto());
                }

                // DS_PRODUTO
                produto_buscado.setDs_produto(produto.getDs_produto());

                // IMG_URL_PRODUTO
                produto_buscado.setImg_url_produto(produto.getImg_url_produto());

                // MARCA_PRODUTO
                if (Objects.nonNull(produto.getMarca_produto())) {
                    produto_buscado.setMarca_produto(produto.getMarca_produto());
                }

                // MENOR_PRECO_PRODUTO
                if (Objects.nonNull(produto.getMenor_preco_produto())) {
                    produto_buscado.setMenor_preco_produto(produto.getMenor_preco_produto());
                }

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