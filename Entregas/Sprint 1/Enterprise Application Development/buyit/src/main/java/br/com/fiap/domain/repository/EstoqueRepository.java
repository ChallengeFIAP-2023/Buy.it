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

    public List<Estoque> findByIdProduto(Long id_produto) {
        String jpql = "SELECT estoque FROM Estoque estoque WHERE estoque.id_produto = :id_produto";
        TypedQuery<Estoque> query = manager.createQuery(jpql, Estoque.class);
        query.setParameter("id_produto", id_produto);
        return query.getResultList();
    }

    public List<Estoque> findByIdValorVariacao(Long id_valor_variacao) {
        String jpql = "SELECT estoque FROM Estoque estoque WHERE estoque.id_valor_variacao = :id_valor_variacao";
        TypedQuery<Estoque> query = manager.createQuery(jpql, Estoque.class);
        query.setParameter("id_valor_variacao", id_valor_variacao);
        return query.getResultList();
    }

    public List<Estoque> findByIdUsuario(Long id_usuario) {
        String jpql = "SELECT estoque FROM Estoque estoque WHERE estoque.id_usuario = :id_usuario";
        TypedQuery<Estoque> query = manager.createQuery(jpql, Estoque.class);
        query.setParameter("id_usuario", id_usuario);
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
            estoque.setId_estoque(null);
            transaction.begin();
            Produto produto = manager.find(Produto.class, estoque.getId_produto().getId_produto());
            Valor_Variacao valor_variacao = manager.find(Valor_Variacao.class, estoque.getId_valor_variacao().getId_valor_variacao());
            Usuario usuario = manager.find(Usuario.class, estoque.getId_usuario().getId_usuario());
            estoque.setId_produto(produto);
            estoque.setId_valor_variacao(valor_variacao);
            estoque.setId_usuario(usuario);
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
            Estoque estoque_buscado = findById(estoque.getId_estoque());
            if (Objects.nonNull(estoque_buscado)) {

                // ID_PRODUTO
                if (Objects.nonNull(estoque.getId_produto())) {
                    Produto produto = manager.find(Produto.class, estoque.getId_produto().getId_produto());
                    estoque_buscado.setId_produto(produto);
                }

                // ID_VALOR_VARIACAO
                if (Objects.nonNull(estoque.getId_valor_variacao())) {
                    Valor_Variacao valor_variacao = manager.find(Valor_Variacao.class, estoque.getId_valor_variacao().getId_valor_variacao());
                    estoque_buscado.setId_valor_variacao(valor_variacao);
                }

                // ID_USUARIO
                if (Objects.nonNull(estoque.getId_usuario())) {
                    Usuario usuario = manager.find(Usuario.class, estoque.getId_usuario().getId_usuario());
                    estoque_buscado.setId_usuario(usuario);
                }

                // QTD_ESTOQUE
                if (Objects.nonNull(estoque.getQtd_estoque())) {
                    estoque_buscado.setQtd_estoque(estoque.getQtd_estoque());
                }

                // PRECO
                if (Objects.nonNull(estoque.getPreco_unitario())) {
                    estoque_buscado.setPreco_unitario(estoque.getPreco_unitario());
                }

                // IMG_URL_ESTOQUE
                estoque_buscado.setImg_url_estoque(estoque.getImg_url_estoque());

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