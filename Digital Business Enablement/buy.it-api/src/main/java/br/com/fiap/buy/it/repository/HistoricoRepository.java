package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Historico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    Optional<Historico> findById(Long id);

    @Query("SELECT t FROM Historico t")
    Page<Historico> findAllWithPagination(Pageable pageable);
}
