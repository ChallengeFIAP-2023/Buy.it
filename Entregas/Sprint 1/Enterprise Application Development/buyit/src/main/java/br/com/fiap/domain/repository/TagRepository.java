package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class TagRepository implements Repository<Tag, Long> {

    private static volatile TagRepository instance;
    private final EntityManager manager;

    private TagRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static TagRepository build(EntityManager manager) {
        TagRepository result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (TagRepository.class) {
            if (Objects.isNull(instance)) {
                instance = new TagRepository(manager);
            }
            return instance;
        }
    }

    @Override
    public Tag findById(Long id) {
        return manager.find(Tag.class, id);
    }

    public List<Tag> findByName(String name) {
        String jpql = "SELECT tag FROM Tag tag  where upper(tag.nm_tag) LIKE CONCAT('%',upper(:nm_tag),'%')";
        TypedQuery<Tag> query = manager.createQuery(jpql, Tag.class);
        query.setParameter("nm_tag", name);
        return query.getResultList();
    }

    @Override
    public List<Tag> findAll() {
        return manager.createQuery("FROM Tag", Tag.class).getResultList();
    }

    @Override
    public Tag persist(Tag tag) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            tag.setId_tag(null);
            transaction.begin();
            tag.setId_tag(null);
            manager.persist(tag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Tag tag_buscada = manager.find(Tag.class, tag.getId_tag());
            if (Objects.nonNull(tag_buscada)) {

                // NM_TAG
                if (Objects.nonNull(tag.getNm_tag())) {
                    tag_buscada.setNm_tag(tag.getNm_tag());
                }

                tag = manager.merge(tag_buscada);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return tag;
    }

    @Override
    public boolean delete(Tag tag) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(tag);
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
