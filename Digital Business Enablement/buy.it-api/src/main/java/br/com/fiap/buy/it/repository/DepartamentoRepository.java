package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Departamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    @Query("SELECT d FROM Departamento d JOIN d.tags t WHERE t.id = :tagId")
    Page<Departamento> findByTagId(@Param("tagId") Long tagId, Pageable pageable);
}