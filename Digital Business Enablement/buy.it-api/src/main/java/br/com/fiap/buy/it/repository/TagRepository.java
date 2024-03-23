package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t JOIN t.departamentos d WHERE d.id = :departamentoId")
    Page<Tag> findByDepartamentoId(@Param("departamentoId") Long departamentoId, Pageable pageable);

    @Query("SELECT t FROM Tag t JOIN t.usuarios u WHERE u.id = :usuarioId")
    Page<Tag> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);

    @Query("SELECT t FROM Tag t JOIN t.produtos p WHERE p.id = :produtoId")
    Page<Tag> findByProdutoId(@Param("produtoId") Long produtoId, Pageable pageable);
}