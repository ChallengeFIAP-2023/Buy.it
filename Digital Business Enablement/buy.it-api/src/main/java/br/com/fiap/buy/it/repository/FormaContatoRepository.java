package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.FormaContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaContatoRepository extends JpaRepository<FormaContato, Long> {

}