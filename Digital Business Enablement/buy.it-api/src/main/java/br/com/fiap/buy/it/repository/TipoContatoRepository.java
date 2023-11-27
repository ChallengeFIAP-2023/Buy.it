package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.TipoContato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoContatoRepository extends JpaRepository<TipoContato, Long> {
    Optional<TipoContato> findById(Long id);

    @Query("SELECT t FROM TipoContato t")
    Page<TipoContato> findAllWithPagination(Pageable pageable);
}