package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Desconto;
import br.com.fiap.domain.entity.Estoque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class DescontoRepository implements Repository<Desconto, Long> {

    private static volatile DescontoRepository instance;
    private final EntityManager manager;

    private DescontoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static DescontoRepository build(EntityManager manager) {
        DescontoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (DescontoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new DescontoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Desconto findById(Long id) {
        return manager.find(Desconto.class, id);
    }


    public List<Desconto> findByIdEstoque(Long id) {
        String jpql = "SELECT desconto FROM Desconto desconto WHERE desconto.estoque.id = :id";
        TypedQuery<Desconto> query = manager.createQuery(jpql, Desconto.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Desconto> findAll() {
        return manager.createQuery("FROM Desconto", Desconto.class).getResultList();
    }

    @Override
    public Desconto persist(Desconto desconto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            desconto.setId(null);
            transaction.begin();
            Estoque estoque = manager.find(Estoque.class, desconto.getEstoque().getId());
            desconto.setEstoque(estoque);
            manager.persist(desconto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return desconto;
    }

    @Override
    public Desconto update(Desconto desconto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Desconto desconto_buscado = manager.find(Desconto.class, desconto.getId());
            if (Objects.nonNull(desconto_buscado)) {

                // ESTOQUE
                if (Objects.nonNull(desconto.getId())) {
                    Estoque estoque = manager.find(Estoque.class, desconto.getEstoque().getId());
                    desconto_buscado.setEstoque(estoque);
                }

                // QTD_MIN_PRODUTO
                if (Objects.nonNull(desconto.getQtdMinProduto())) {
                    desconto_buscado.setQtdMinProduto(desconto.getQtdMinProduto());
                }

                // DESCONTO
                if (Objects.nonNull(desconto.getDesconto())) {
                    desconto_buscado.setDesconto(desconto.getDesconto());
                }

                desconto = manager.merge(desconto_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return desconto;
    }

    @Override
    public boolean delete(Desconto desconto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(desconto);
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
