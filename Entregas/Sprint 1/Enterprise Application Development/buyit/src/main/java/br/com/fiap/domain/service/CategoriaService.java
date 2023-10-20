package br.com.fiap.domain.service;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.Categoria;
import br.com.fiap.domain.repository.CategoriaRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

public class CategoriaService implements Service<Categoria, Long> {

    private static volatile CategoriaService instance;

    private final CategoriaRepository repo;

    private CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public static CategoriaService build() {
        String persistenceUnit = Main.PERSISTENCE_UNIT;
        CategoriaService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (CategoriaService.class) {
            if (Objects.isNull(instance)) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build(persistenceUnit).provide();
                CategoriaRepository avaliacaoRepository = CategoriaRepository.build(factory.createEntityManager());
                instance = new CategoriaService(avaliacaoRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Categoria> findAll() {
        return repo.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return repo.findById(id);
    }

    public List<Categoria> findByName(String name) {
        if (Objects.isNull(name)) return null;
        return repo.findByName(name);
    }

    @Override
    public Categoria persist(Categoria categoria) {
        if (Objects.isNull(categoria)) return null;
        return repo.persist(categoria);
    }

    @Override
    public Categoria update(Categoria categoria) {
        if (Objects.isNull(categoria)) return null;
        return repo.update(categoria);
    }

    @Override
    public boolean delete(Categoria categoria) {
        if (Objects.isNull(categoria)) return false;
        return repo.delete(categoria);
    }
}
