package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Avaliacao;
import br.com.fiap.domain.entity.Pedido;
import br.com.fiap.domain.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class AvaliacaoRepository implements Repository<Avaliacao, Long> {

    private static volatile AvaliacaoRepository instance;
    private final EntityManager manager;

    private AvaliacaoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static AvaliacaoRepository build(EntityManager manager) {
        AvaliacaoRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (AvaliacaoRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new AvaliacaoRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Avaliacao findById(Long id) {
        return manager.find(Avaliacao.class, id);
    }

    public List<Avaliacao> findByIdUsuario(Long id) {
        String jpql = "SELECT avaliacao FROM Avaliacao avaliacao WHERE avaliacao.usuario.id = :id";
        TypedQuery<Avaliacao> query = manager.createQuery(jpql, Avaliacao.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public Avaliacao findByIdPedido(Long id) {
        return manager.find(Avaliacao.class, id);
    }

    @Override
    public List<Avaliacao> findAll() {
        return manager.createQuery("FROM Avaliacao", Avaliacao.class).getResultList();
    }

    @Override
    public Avaliacao persist(Avaliacao avaliacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            avaliacao.setId(null);
            transaction.begin();
            Usuario usuario = manager.find(Usuario.class, avaliacao.getUsuario().getId());
            Pedido pedido = manager.find(Pedido.class, avaliacao.getPedido().getId());
            avaliacao.setUsuario(usuario);
            avaliacao.setPedido(pedido);
            manager.persist(avaliacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return avaliacao;
    }

    @Override
    public Avaliacao update(Avaliacao avaliacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Avaliacao avaliacao_buscada = findById(avaliacao.getId());
            if (Objects.nonNull(avaliacao_buscada)) {

                // USUARIO
                if (Objects.nonNull(avaliacao.getUsuario())) {
                    Usuario usuario = manager.find(Usuario.class, avaliacao.getUsuario().getId());
                    avaliacao_buscada.setUsuario(usuario);
                }

                // PEDIDO
                if (Objects.nonNull(avaliacao.getPedido())) {
                    Pedido pedido = manager.find(Pedido.class, avaliacao.getPedido().getId());
                    avaliacao_buscada.setPedido(pedido);
                }

                // NOTA_PRECO
                avaliacao_buscada.setNotaPreco(avaliacao.getNotaPreco());

                // NOTA_QUALIDADE
                avaliacao_buscada.setNotaQualidade(avaliacao.getNotaQualidade());

                // NOTA_ENTREGA
                avaliacao_buscada.setNotaEntrega(avaliacao.getNotaEntrega());

                // DESCRICAO
                avaliacao_buscada.setDescricao(avaliacao.getDescricao());

                manager.merge(avaliacao_buscada);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return avaliacao;
    }

    @Override
    public boolean delete(Avaliacao avaliacao) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(avaliacao);
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