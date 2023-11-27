package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}