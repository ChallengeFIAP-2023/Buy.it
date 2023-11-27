package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findById(Long id);

    @Query("SELECT t FROM Departamento t")
    Page<Departamento> findAllWithPagination(Pageable pageable);
}