package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.cotacao.id = ?1")
    Optional<Avaliacao> findByIdCotacao(Long id);
    
}