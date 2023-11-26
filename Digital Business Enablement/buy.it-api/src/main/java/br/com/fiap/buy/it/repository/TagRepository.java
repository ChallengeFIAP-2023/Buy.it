package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findById(Long id);

    @Query("SELECT t FROM Tag t")
    Page<Tag> findAllWithPagination(Pageable pageable);
}

