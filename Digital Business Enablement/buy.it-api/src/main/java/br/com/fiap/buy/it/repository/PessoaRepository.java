package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findById(Long id);

    @Query("SELECT t FROM Pessoa t")
    Page<Pessoa> findAllWithPagination(Pageable pageable);
}