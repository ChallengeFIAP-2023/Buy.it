package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Pedido;
import br.com.fiap.domain.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class PedidoRepository implements Repository<Pedido, Long> {

    private static volatile PedidoRepository instance;
    private final EntityManager manager;

    private PedidoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static PedidoRepository build(EntityManager manager) {
        PedidoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (PedidoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new PedidoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Pedido findById(Long id) {
        return manager.find(Pedido.class, id);
    }

    public List<Pedido> findByIdUsuario(Long id) {
        String jpql = "SELECT pedido FROM Pedido pedido WHERE pedido.usuario.id = :id";
        TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Pedido> findAll() {
        return manager.createQuery("FROM Pedido", Pedido.class).getResultList();
    }

    @Override
    public Pedido persist(Pedido pedido) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            pedido.setId(null);
            transaction.begin();
            Usuario usuario = manager.find(Usuario.class, pedido.getUsuario().getId());
            pedido.setUsuario(usuario);
            manager.persist(pedido);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return pedido;
    }

    @Override
    public Pedido update(Pedido pedido) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Pedido pedido_buscado = findById(pedido.getId());
            if (Objects.nonNull(pedido_buscado)) {

                // USUARIO
                if (Objects.nonNull(pedido.getUsuario())) {
                    Usuario usuario = manager.find(Usuario.class, pedido.getUsuario().getId());
                    pedido_buscado.setUsuario(usuario);
                }

                // STATUS
                if (Objects.nonNull(pedido.getStatus())) {
                    pedido_buscado.setStatus(pedido.getStatus());
                }

                // DATA
                if (Objects.nonNull(pedido.getData())) {
                    pedido_buscado.setData(pedido.getData());
                }

                // VALOR
                if (Objects.nonNull(pedido.getValor())) {
                    pedido_buscado.setValor(pedido.getValor());
                }

                manager.merge(pedido_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return pedido;
    }

    @Override
    public boolean delete(Pedido pedido) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(pedido);
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