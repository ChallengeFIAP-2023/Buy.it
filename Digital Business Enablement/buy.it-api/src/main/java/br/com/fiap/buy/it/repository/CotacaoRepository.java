package br.com.fiap.buy.it.repository;

import br.com.fiap.buy.it.model.Cotacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
    Page<Cotacao> findByCompradorId(Long compradorId, Pageable pageable);

    @Query("SELECT MIN(c.valorProduto), AVG(c.valorProduto), MAX(c.valorProduto) FROM Cotacao c JOIN c.status s WHERE c.produto.id = :idProduto AND s.nome = 'Concluído'")
    Optional<Object> getInfoByProdutoId(@Param("idProduto") Long idProduto);
}