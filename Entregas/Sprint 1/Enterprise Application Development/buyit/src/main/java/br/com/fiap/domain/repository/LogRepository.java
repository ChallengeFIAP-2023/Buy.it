package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Log;
import br.com.fiap.domain.entity.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class LogRepository implements Repository<Log, Long> {

    private static volatile LogRepository instance;
    private final EntityManager manager;

    private LogRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static LogRepository build(EntityManager manager) {
        LogRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (LogRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new LogRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Log findById(Long id) {
        return manager.find(Log.class, id);
    }

    public List<Log> findByIdPedido(Long id) {
        String jpql = "SELECT log FROM Log log WHERE log.pedido = :id";
        TypedQuery<Log> query = manager.createQuery(jpql, Log.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Log> findByName(String nome) {
        String jpql = "SELECT log FROM Log log  where upper(log.nome) LIKE CONCAT('%',upper(:nome),'%')";
        TypedQuery<Log> query = manager.createQuery(jpql, Log.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<Log> findAll() {
        return manager.createQuery("FROM Log", Log.class).getResultList();
    }

    @Override
    public Log persist(Log log) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            log.setId(null);
            transaction.begin();
            Pedido pedido = manager.find(Pedido.class, log.getPedido().getId());
            log.setPedido(pedido);
            manager.persist(log);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return log;
    }

    @Override
    public Log update(Log log) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Log log_buscado = manager.find(Log.class, log.getId());
            if (Objects.nonNull(log_buscado)) {

                // ID_PEDIDO
                if (Objects.nonNull(log.getPedido())) {
                    Pedido pedido = manager.find(Pedido.class, log.getPedido().getId());
                    log_buscado.setPedido(pedido);
                }

                // TIMESTAMP_LOG
                if (log.getTimestamp() != null) {
                    log_buscado.setTimestamp(log.getTimestamp());
                }

                // NM_LOG
                if (log.getNome() != null) {
                    log_buscado.setNome(log.getNome());
                }

                // DS_LOG
                if (log.getDescricao() != null) {
                    log_buscado.setDescricao(log.getDescricao());
                }

                log = manager.merge(log_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return log;
    }

    @Override
    public boolean delete(Log log) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(log);
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
