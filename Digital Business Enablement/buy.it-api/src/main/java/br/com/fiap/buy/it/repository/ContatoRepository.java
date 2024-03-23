package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Contato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Page<Contato> findByUsuarioId(Long userId, Pageable pageable);
}