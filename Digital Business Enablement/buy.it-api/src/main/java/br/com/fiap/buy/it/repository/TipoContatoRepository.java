package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContatoRepository extends JpaRepository<TipoContato, Long> {
    
}