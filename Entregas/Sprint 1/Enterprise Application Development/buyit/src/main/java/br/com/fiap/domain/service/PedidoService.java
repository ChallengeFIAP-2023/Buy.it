package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Pedido;
import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.repository.PedidoRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class PedidoService implements Service<Pedido, Long> {

    private static volatile PedidoService instance;
    private final PedidoRepository repo;

    private PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public static PedidoService build(EntityManager manager) {
        PedidoService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (PedidoService.class) {
            if (Objects.isNull(instance)) {
                PedidoRepository pedidoRepository = PedidoRepository.getInstance(manager);
                instance = new PedidoService(pedidoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Pedido> findAll() {
        return repo.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return repo.findById(id);
    }

    public Usuario findByIdUsuario(Long id_usuario) {
        if (Objects.isNull(id_usuario)) return null;
        return repo.findByIdUsuario(id_usuario);
    }

    @Override
    public Pedido persist(Pedido pedido) {
        if (Objects.isNull(pedido)) return null;
        return repo.persist(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        if (Objects.isNull(pedido)) return null;
        return repo.update(pedido);
    }

    @Override
    public boolean delete(Pedido pedido) {
        if (Objects.isNull(pedido)) return false;
        return repo.delete(pedido);
    }
}