package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findById(Long id);

    @Query("SELECT t FROM Status t")
    Page<Status> findAllWithPagination(Pageable pageable);
}