package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.FormaContato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormaContatoRepository extends JpaRepository<FormaContato, Long> {
    Optional<FormaContato> findById(Long id);

    @Query("SELECT c FROM FormaContato c WHERE c.id = ?1")
    Page<FormaContato> findByUserIdWithPagination(Long id, Pageable pageable);
}