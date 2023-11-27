package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Cotacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    Optional<Cotacao> findById(Long id);

    @Query("SELECT t FROM Cotacao t")
    Page<Cotacao> findAllWithPagination(Pageable pageable);
}