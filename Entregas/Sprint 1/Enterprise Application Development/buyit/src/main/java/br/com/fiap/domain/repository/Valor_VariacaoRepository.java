package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Tipo_Variacao;
import br.com.fiap.domain.entity.Valor_Variacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class Valor_VariacaoRepository implements Repository<Valor_Variacao, Long> {

    private static Valor_VariacaoRepository instance;
    private final EntityManager manager;

    private Valor_VariacaoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static synchronized Valor_VariacaoRepository getInstance(EntityManager manager) {
        if (instance == null) {
            instance = new Valor_VariacaoRepository(manager);
        }
        return instance;
    }

    @Override
    public Valor_Variacao findById(Long id) {
        return manager.find(Valor_Variacao.class, id);
    }

    public Tipo_Variacao findByIdTipoVariacao(Long id) {
        return manager.find(Tipo_Variacao.class, id);
    }

    public List<Valor_Variacao> findByName(String name) {
        String jpql = "SELECT valor_variacao FROM Valor_Variacao valor_variacao  where upper(valor_variacao.nm_valor_variacao) LIKE CONCAT('%',upper(:nm_valor_variacao),'%')";
        TypedQuery<Valor_Variacao> query = manager.createQuery(jpql, Valor_Variacao.class);
        query.setParameter("nm_valor_variacao", name);
        return query.getResultList();
    }

    @Override
    public List<Valor_Variacao> findAll() {
        return manager.createQuery("FROM Valor_Variacao", Valor_Variacao.class).getResultList();
    }

    @Override
    public Valor_Variacao persist(Valor_Variacao valor_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            valor_variacao.setId_valor_variacao(null);
            transaction.begin();
            Tipo_Variacao tipo_variacao = findByIdTipoVariacao(valor_variacao.getId_tipo_variacao().getId_tipo_variacao());
            valor_variacao.setId_tipo_variacao(tipo_variacao);
            manager.persist(valor_variacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return valor_variacao;
    }

    @Override
    public Valor_Variacao update(Valor_Variacao valor_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Valor_Variacao valor_variacao_buscado = manager.find(Valor_Variacao.class, valor_variacao.getId_valor_variacao());
            if (Objects.nonNull(valor_variacao_buscado)) {

                // ID_TIPO_VARIACAO
                if (Objects.nonNull(valor_variacao.getId_tipo_variacao())) {
                    Tipo_Variacao tipo_variacao = findByIdTipoVariacao(valor_variacao.getId_tipo_variacao().getId_tipo_variacao());
                    valor_variacao_buscado.setId_tipo_variacao(tipo_variacao);
                }

                // NM_VALOR_VARIACAO
                if (valor_variacao.getNm_valor_variacao() != null) {
                    valor_variacao_buscado.setNm_valor_variacao(valor_variacao.getNm_valor_variacao());
                }

                valor_variacao = manager.merge(valor_variacao_buscado);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return valor_variacao;
    }

    @Override
    public boolean delete(Valor_Variacao valor_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(valor_variacao);
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
