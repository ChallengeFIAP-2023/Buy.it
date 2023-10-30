package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class CategoriaRepository implements Repository<Categoria, Long> {

    private static volatile CategoriaRepository instance;
    private final EntityManager manager;

    private CategoriaRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static CategoriaRepository build(EntityManager manager) {
        CategoriaRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (CategoriaRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new CategoriaRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Categoria findById(Long id) {
        return manager.find(Categoria.class, id);
    }

    public List<Categoria> findByName(String nome) {
        String jpql = "SELECT categoria FROM Categoria categoria  where upper(categoria.nome) LIKE CONCAT('%',upper(:nome),'%')";
        TypedQuery<Categoria> query = manager.createQuery(jpql, Categoria.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<Categoria> findAll() {
        return manager.createQuery("FROM Categoria", Categoria.class).getResultList();
    }

    @Override
    public Categoria persist(Categoria categoria) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            categoria.setId(null);
            transaction.begin();
            manager.persist(categoria);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return categoria;
    }

    @Override
    public Categoria update(Categoria categoria) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Categoria categoria_buscada = manager.find(Categoria.class, categoria.getId());
            if (Objects.nonNull(categoria_buscada)) {

                // NOME
                if (Objects.nonNull(categoria.getNome())) {
                    categoria_buscada.setNome(categoria.getNome());
                }

                // ICONE
                categoria_buscada.setIcone(categoria.getIcone());

                categoria = manager.merge(categoria_buscada);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return categoria;
    }

    @Override
    public boolean delete(Categoria categoria) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(categoria);
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
