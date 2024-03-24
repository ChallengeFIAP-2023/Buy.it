package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findByDepartamentoId(Long departamentoId, Pageable pageable);

    @Query("SELECT p FROM Produto p JOIN p.tags t WHERE t.id = :tagId")
    Page<Produto> findByTagId(@Param("tagId") Long tagId, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(concat('%', :nome, '%'))")
    Page<Produto> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);
}