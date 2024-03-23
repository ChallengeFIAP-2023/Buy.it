package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Avaliacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    Page<Avaliacao> findByCotacaoId(Long cotacaoId, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a JOIN a.cotacao c WHERE c.comprador.id = :usuarioId")
    Page<Avaliacao> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);
}