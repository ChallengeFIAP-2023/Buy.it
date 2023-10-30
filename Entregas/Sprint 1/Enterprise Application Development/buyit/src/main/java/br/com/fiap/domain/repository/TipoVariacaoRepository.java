package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.TipoVariacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class TipoVariacaoRepository implements Repository<TipoVariacao, Long> {

    private static volatile TipoVariacaoRepository instance;
    private final EntityManager manager;

    private TipoVariacaoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static TipoVariacaoRepository build(EntityManager manager) {
        TipoVariacaoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (TipoVariacaoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new TipoVariacaoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public TipoVariacao findById(Long id) {
        return manager.find(TipoVariacao.class, id);
    }

    public List<TipoVariacao> findByName(String nome) {
        String jpql = "SELECT tipo_variacao FROM TipoVariacao tipo_variacao  where upper(tipo_variacao.nome) LIKE CONCAT('%',upper(:nome),'%')";
        TypedQuery<TipoVariacao> query = manager.createQuery(jpql, TipoVariacao.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<TipoVariacao> findAll() {
        return manager.createQuery("FROM TipoVariacao", TipoVariacao.class).getResultList();
    }

    @Override
    public TipoVariacao persist(TipoVariacao tipo_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            tipo_variacao.setId(null);
            transaction.begin();
            manager.persist(tipo_variacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return tipo_variacao;
    }

    @Override
    public TipoVariacao update(TipoVariacao tipo_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            TipoVariacao tipo_variacao_buscado = manager.find(TipoVariacao.class, tipo_variacao.getId());
            if (Objects.nonNull(tipo_variacao_buscado)) {

                // NOME
                if (Objects.nonNull(tipo_variacao.getNome())) {
                    tipo_variacao_buscado.setNome(tipo_variacao.getNome());
                }

                tipo_variacao = manager.merge(tipo_variacao_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return tipo_variacao;
    }

    @Override
    public boolean delete(TipoVariacao tipo_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(tipo_variacao);
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
