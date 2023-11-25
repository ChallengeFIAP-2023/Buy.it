package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findById(Long id);

    @Query("SELECT t FROM Produto t")
    Page<Produto> findAllWithPagination(Pageable pageable);
}

