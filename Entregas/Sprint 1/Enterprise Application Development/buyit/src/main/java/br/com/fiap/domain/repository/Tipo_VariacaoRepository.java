package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Tipo_Variacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class Tipo_VariacaoRepository implements Repository<Tipo_Variacao, Long> {

    private static volatile Tipo_VariacaoRepository instance;
    private final EntityManager manager;

    private Tipo_VariacaoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static Tipo_VariacaoRepository build(EntityManager manager) {
        Tipo_VariacaoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (Tipo_VariacaoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new Tipo_VariacaoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Tipo_Variacao findById(Long id) {
        return manager.find(Tipo_Variacao.class, id);
    }

    public List<Tipo_Variacao> findByName(String name) {
        String jpql = "SELECT tipo_variacao FROM Tipo_Variacao tipo_variacao  where upper(tipo_variacao.nm_tipo_variacao) LIKE CONCAT('%',upper(:nm_tipo_variacao),'%')";
        TypedQuery<Tipo_Variacao> query = manager.createQuery(jpql, Tipo_Variacao.class);
        query.setParameter("nm_tipo_variacao", name);
        return query.getResultList();
    }

    @Override
    public List<Tipo_Variacao> findAll() {
        return manager.createQuery("FROM Tipo_Variacao", Tipo_Variacao.class).getResultList();
    }

    @Override
    public Tipo_Variacao persist(Tipo_Variacao tipo_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            tipo_variacao.setId_tipo_variacao(null);
            transaction.begin();
            tipo_variacao.setId_tipo_variacao(null);
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
    public Tipo_Variacao update(Tipo_Variacao tipo_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Tipo_Variacao tipo_variacao_buscado = manager.find(Tipo_Variacao.class, tipo_variacao.getId_tipo_variacao());
            if (Objects.nonNull(tipo_variacao_buscado)) {

                // NM_TIPO_VARIACAO
                if (Objects.nonNull(tipo_variacao.getNm_tipo_variacao())) {
                    tipo_variacao_buscado.setNm_tipo_variacao(tipo_variacao.getNm_tipo_variacao());
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
    public boolean delete(Tipo_Variacao tipo_variacao) {
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
