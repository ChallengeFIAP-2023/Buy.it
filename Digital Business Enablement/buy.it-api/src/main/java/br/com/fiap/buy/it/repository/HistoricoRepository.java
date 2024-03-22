package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Historico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    Page<Historico> findByCotacaoId(Long cotacaoId, Pageable pageable);

    Page<Historico> findByFornecedorId(Long fornecedorId, Pageable pageable);

    Page<Historico> findByStatusId(Long statusId, Pageable pageable);
}