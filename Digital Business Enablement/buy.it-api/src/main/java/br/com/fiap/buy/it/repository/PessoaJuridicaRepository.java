package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.PessoaJuridica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    Optional<PessoaJuridica> findById(Long id);

    @Query("SELECT t FROM PessoaJuridica t")
    Page<PessoaJuridica> findAllWithPagination(Pageable pageable);
}
