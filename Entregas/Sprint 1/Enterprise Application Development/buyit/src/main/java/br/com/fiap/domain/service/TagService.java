package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Tag;
import br.com.fiap.domain.repository.TagRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class TagService implements Service<Tag, Long> {

    private static volatile TagService instance;
    private final TagRepository repo;

    private TagService(TagRepository repo) {
        this.repo = repo;
    }

    public static TagService build(EntityManager manager) {
        TagService result = instance;
        if (Objects.nonNull(result)) return result;

        synchronized (TagService.class) {
            if (Objects.isNull(instance)) {
                TagRepository tagRepository = TagRepository.getInstance(manager);
                instance = new TagService(tagRepository);
            }
            return instance;
        }
    }

    @Override
    public List<Tag> findAll() {
        return repo.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return repo.findById(id);
    }

    public List<Tag> findByName(String name) {
        if (Objects.isNull(name)) return null;
        return repo.findByName(name);
    }

    @Override
    public Tag persist(Tag tag) {
        if (Objects.isNull(tag)) return null;
        return repo.persist(tag);
    }

    @Override
    public Tag update(Tag tag) {
        if (Objects.isNull(tag)) return null;
        return repo.update(tag);
    }

    @Override
    public boolean delete(Tag tag) {
        if (Objects.isNull(tag)) return false;
        return repo.delete(tag);
    }
}