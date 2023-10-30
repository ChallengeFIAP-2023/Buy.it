package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class EstoqueRepository implements Repository<Estoque, Long> {

    private static volatile EstoqueRepository instance;
    private final EntityManager manager;

    private EstoqueRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static EstoqueRepository build(EntityManager manager) {
        EstoqueRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (EstoqueRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new EstoqueRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Estoque findById(Long id) {
        return manager.find(Estoque.class, id);
    }

    public List<Estoque> findByIdProduto(Long id) {
        String jpql = "SELECT estoque FROM Estoque estoque WHERE estoque.produto.id = :id";
        TypedQuery<Estoque> query = manager.createQuery(jpql, Estoque.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Estoque> findByIdValorVariacao(Long id) {
        String jpql = "SELECT estoque FROM Estoque estoque WHERE estoque.valorVariacao.id = :id";
        TypedQuery<Estoque> query = manager.createQuery(jpql, Estoque.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Estoque> findByIdUsuario(Long id) {
        String jpql = "SELECT estoque FROM Estoque estoque WHERE estoque.usuario.id = :id";
        TypedQuery<Estoque> query = manager.createQuery(jpql, Estoque.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Estoque> findAll() {
        return manager.createQuery("FROM Estoque", Estoque.class).getResultList();
    }

    @Override
    public Estoque persist(Estoque estoque) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            estoque.setId(null);
            transaction.begin();
            Produto produto = manager.find(Produto.class, estoque.getProduto().getId());
            ValorVariacao valor_variacao = manager.find(ValorVariacao.class, estoque.getValorVariacao().getId());
            Usuario usuario = manager.find(Usuario.class, estoque.getUsuario().getId());
            estoque.setProduto(produto);
            estoque.setValorVariacao(valor_variacao);
            estoque.setUsuario(usuario);
            manager.persist(estoque);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return estoque;
    }

    @Override
    public Estoque update(Estoque estoque) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Estoque estoque_buscado = findById(estoque.getId());
            if (Objects.nonNull(estoque_buscado)) {

                // PRODUTO
                if (Objects.nonNull(estoque.getProduto())) {
                    Produto produto = manager.find(Produto.class, estoque.getProduto().getId());
                    estoque_buscado.setProduto(produto);
                }

                // VALOR_VARIACAO
                if (Objects.nonNull(estoque.getValorVariacao())) {
                    ValorVariacao valor_variacao = manager.find(ValorVariacao.class, estoque.getValorVariacao().getId());
                    estoque_buscado.setValorVariacao(valor_variacao);
                }

                // USUARIO
                if (Objects.nonNull(estoque.getUsuario())) {
                    Usuario usuario = manager.find(Usuario.class, estoque.getUsuario().getId());
                    estoque_buscado.setUsuario(usuario);
                }

                // QUANTIDADE
                if (Objects.nonNull(estoque.getQuantidade())) {
                    estoque_buscado.setQuantidade(estoque.getQuantidade());
                }

                // PRECO
                if (Objects.nonNull(estoque.getPrecoUnitario())) {
                    estoque_buscado.setPrecoUnitario(estoque.getPrecoUnitario());
                }

                // IMG_URL
                estoque_buscado.setImgUrl(estoque.getImgUrl());

                manager.merge(estoque_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return estoque;
    }

    @Override
    public boolean delete(Estoque estoque) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(estoque);
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