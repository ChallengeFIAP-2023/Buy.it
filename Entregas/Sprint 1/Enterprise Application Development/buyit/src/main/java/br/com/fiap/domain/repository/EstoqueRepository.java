package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Estoque;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.entity.Valor_Variacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Objects;

public class EstoqueRepository implements Repository<Estoque, Long> {

    private static EstoqueRepository instance;
    private final EntityManager manager;

    private EstoqueRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static synchronized EstoqueRepository getInstance(EntityManager manager) {
        if (instance == null) {
            instance = new EstoqueRepository(manager);
        }
        return instance;
    }

    @Override
    public Estoque findById(Long id) {
        return manager.find(Estoque.class, id);
    }

    public Produto findByIdProduto(Long id) {
        return manager.find(Produto.class, id);
    }

    public Valor_Variacao findByIdValorVariacao(Long id) {
        return manager.find(Valor_Variacao.class, id);
    }

    public Usuario findByIdUsuario(Long id) {
        return manager.find(Usuario.class, id);
    }

    @Override
    public List<Estoque> findAll() {
        return manager.createQuery("FROM Estoque", Estoque.class).getResultList();
    }

    @Override
    public Estoque persist(Estoque estoque) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Produto produto = findByIdProduto(estoque.getId_usuario().getId_usuario());
            Valor_Variacao valor_variacao = findByIdValorVariacao(estoque.getId_valor_variacao().getId_valor_variacao());
            estoque.setId_produto(produto);
            estoque.setId_valor_variacao(valor_variacao);
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
                    Produto produto = findByIdProduto(estoque.getId_produto().getId_produto());
                    estoque_buscado.setId_produto(produto);
                }

                // ID_VALOR_VARIACAO
                if (Objects.nonNull(estoque.getId_valor_variacao())) {
                    Valor_Variacao valor_variacao = findByIdValorVariacao(estoque.getId_valor_variacao().getId_valor_variacao());
                    estoque_buscado.setId_valor_variacao(valor_variacao);
                }

                // ID_USUARIO
                if (Objects.nonNull(estoque.getId_usuario())) {
                    Usuario usuario = findByIdUsuario(estoque.getId_usuario().getId_usuario());
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