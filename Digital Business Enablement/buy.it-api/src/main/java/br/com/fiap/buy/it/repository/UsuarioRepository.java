package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u JOIN u.tags t WHERE t.id = :tagId")
    Page<Usuario> findByTagId(@Param("tagId") Long tagId, Pageable pageable);
}