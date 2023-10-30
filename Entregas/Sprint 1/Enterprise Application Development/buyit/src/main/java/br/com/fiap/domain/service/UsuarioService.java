package br.com.fiap.domain.service;

import br.com.fiap.BuyitApplicattion;
import br.com.fiap.domain.entity.Usuario;
import br.com.fiap.domain.repository.UsuarioRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class UsuarioService implements Service<Usuario, Long> {

    private static volatile UsuarioService instance;

    private final UsuarioRepository repo;

    private UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public static UsuarioService build() {
        String persistenceUnit = BuyitApplicattion.PERSISTENCE_UNIT;
        UsuarioService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (UsuarioService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                UsuarioRepository avaliacaoRepository = UsuarioRepository.build(factory.createEntityManager());
                instance = new UsuarioService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id);
    }

    public List<Usuario> findByName(String nome) {
        if (Objects.isNull(nome)) return null;
        return repo.findByName(nome);
    }

    public Usuario findByCnpj(String cnpj) {
        if (Objects.isNull(cnpj)) return null;
        return repo.findByCnpj(cnpj);
    }

    @Override
    public Usuario persist(Usuario usuario) {
        if (Objects.isNull(usuario)) return null;
        return repo.persist(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        if (Objects.isNull(usuario)) return null;
        return repo.update(usuario);
    }

    @Override
    public boolean delete(Usuario usuario) {
        if (Objects.isNull(usuario)) return false;
        return repo.delete(usuario);
    }
}
