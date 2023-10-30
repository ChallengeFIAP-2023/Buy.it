package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.TipoVariacao;
import br.com.fiap.domain.entity.ValorVariacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class ValorVariacaoRepository implements Repository<ValorVariacao, Long> {

    private static volatile ValorVariacaoRepository instance;
    private final EntityManager manager;

    private ValorVariacaoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static ValorVariacaoRepository build(EntityManager manager) {
        ValorVariacaoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (ValorVariacaoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new ValorVariacaoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public ValorVariacao findById(Long id) {
        return manager.find(ValorVariacao.class, id);
    }

    public List<ValorVariacao> findByIdTipoVariacao(Long id) {
        String jpql = "SELECT valor_variacao FROM ValorVariacao valor_variacao WHERE valor_variacao.tipoVariacao = :id";
        TypedQuery<ValorVariacao> query = manager.createQuery(jpql, ValorVariacao.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<ValorVariacao> findByName(String nome) {
        String jpql = "SELECT valor_variacao FROM ValorVariacao valor_variacao  where upper(valor_variacao.nome) LIKE CONCAT('%',upper(:nome),'%')";
        TypedQuery<ValorVariacao> query = manager.createQuery(jpql, ValorVariacao.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<ValorVariacao> findAll() {
        return manager.createQuery("FROM ValorVariacao", ValorVariacao.class).getResultList();
    }

    @Override
    public ValorVariacao persist(ValorVariacao valor_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            valor_variacao.setId(null);
            transaction.begin();
            TipoVariacao tipo_variacao = manager.find(TipoVariacao.class, valor_variacao.getTipoVariacao().getId());
            valor_variacao.setTipoVariacao(tipo_variacao);
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
    public ValorVariacao update(ValorVariacao valor_variacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            ValorVariacao valor_variacao_buscado = manager.find(ValorVariacao.class, valor_variacao.getId());
            if (Objects.nonNull(valor_variacao_buscado)) {

                // ID_TIPO_VARIACAO
                if (Objects.nonNull(valor_variacao.getTipoVariacao())) {
                    TipoVariacao tipo_variacao = manager.find(TipoVariacao.class, valor_variacao.getTipoVariacao().getId());
                    valor_variacao_buscado.setTipoVariacao(tipo_variacao);
                }

                // NM_VALOR_VARIACAO
                if (valor_variacao.getNome() != null) {
                    valor_variacao_buscado.setNome(valor_variacao.getNome());
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
    public boolean delete(ValorVariacao valor_variacao) {
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
