package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Estoque;
import br.com.fiap.domain.entity.Produto;
import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.entity.Valor_Variacao;
import br.com.fiap.domain.repository.EstoqueRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class EstoqueService implements Service<Estoque, Long> {

    private static volatile EstoqueService instance;

    private final EstoqueRepository repo;

    private EstoqueService(EstoqueRepository repo) {
        this.repo = repo;
    }

    public static EstoqueService build(EntityManager manager) {
        EstoqueService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (EstoqueService.class) {
            if (Objects.isNull(instance)) {
                EstoqueRepository estoqueRepository = EstoqueRepository.getInstance(manager);
                instance = new EstoqueService(estoqueRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Estoque> findAll() {
        return repo.findAll();
    }

    @Override
    public Estoque findById(Long id) {
        return repo.findById(id);
    }

    public Produto findByIdProduto(Long id_produto) {
        if (Objects.isNull(id_produto)) return null;
        return repo.findByIdProduto(id_produto);
    }

    public Valor_Variacao findByIdValor_Variacao(Long id_estoque) {
        if (Objects.isNull(id_estoque)) return null;
        return repo.findByIdValorVariacao(id_estoque);
    }

    public Usuario findByIdUsuario(Long id_usuario) {
        if (Objects.isNull(id_usuario)) return null;
        return repo.findByIdUsuario(id_usuario);
    }

    @Override
    public Estoque persist(Estoque estoque) {
        if (Objects.isNull(estoque)) return null;
        return repo.persist(estoque);
    }

    @Override
    public Estoque update(Estoque estoque) {
        if (Objects.isNull(estoque)) return null;
        return repo.update(estoque);
    }

    @Override
    public boolean delete(Estoque estoque) {
        if (Objects.isNull(estoque)) return false;
        return repo.delete(estoque);
    }
}
