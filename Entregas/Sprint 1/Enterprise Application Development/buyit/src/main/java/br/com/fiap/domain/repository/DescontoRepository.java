package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Desconto;
import br.com.fiap.domain.entity.Estoque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Objects;

public class DescontoRepository implements Repository<Desconto, Long> {

    private static DescontoRepository instance;
    private final EntityManager manager;

    private DescontoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static synchronized DescontoRepository getInstance(EntityManager manager) {
        if (instance == null) {
            instance = new DescontoRepository(manager);
        }
        return instance;
    }

    @Override
    public Desconto findById(Long id) {
        return manager.find(Desconto.class, id);
    }

    public Estoque findByIdEstoque(Long id) {
        return manager.find(Estoque.class, id);
    }

    @Override
    public List<Desconto> findAll() {
        return manager.createQuery("FROM Desconto", Desconto.class).getResultList();
    }

    @Override
    public Desconto persist(Desconto desconto) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            desconto.setId_desconto(null);
            Estoque estoque = findByIdEstoque(desconto.getId_estoque().getId_estoque());
            desconto.setId_estoque(estoque);
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
            Desconto desconto_buscado = manager.find(Desconto.class, desconto.getId_desconto());
            if (Objects.nonNull(desconto_buscado)) {

                // ID_ESTOQUE
                if (Objects.nonNull(desconto.getId_estoque())) {
                    Estoque estoque = findByIdEstoque(desconto.getId_estoque().getId_estoque());
                    desconto_buscado.setId_estoque(estoque);
                }

                // QTD_MIN_PRODUTO
                if (Objects.nonNull(desconto.getQtd_min_produto())) {
                    desconto_buscado.setQtd_min_produto(desconto.getQtd_min_produto());
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
