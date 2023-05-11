package com.elojao1real.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public class Produto {

	@Repository
	public interface ProdutoRepository extends JpaRepository<Produto, Long> {
		public List<Produto> findByPrecoBetween(@Param("inicio") BigDecimal inicio, @Param("fim") BigDecimal fim);

		public List<Produto> findAllByNomeProdutoContainingIgnoreCase(@Param("nomeProduto") String nomeProduto);

	}
}
