package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findById(long id);
    
    @Query("SELECT u FROM Usuario u")
    Page<Usuario> findAllWithPagination(Pageable pageable);

    Optional<Usuario> findByEmail(String email);
}